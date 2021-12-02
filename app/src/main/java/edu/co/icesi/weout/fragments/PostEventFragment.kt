package edu.co.icesi.weout.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.common.io.Files.getFileExtension
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.HomeActivity
import edu.co.icesi.weout.databinding.FragmentPostEventBinding
import edu.co.icesi.weout.model.Post
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Toast

import edu.co.icesi.weout.activities.MainActivity

import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import com.google.firebase.storage.UploadTask

import android.graphics.ColorSpace.Model

import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.OnProgressListener


class PostEventFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var _binding: FragmentPostEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var states: List<String>
    private var actualState: String = ""

    private var currentImage: String = ""

    private var selectedCoords: String = ""
    val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

    val photoArray:ArrayList<String> = ArrayList<String>()

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    //day, month, year, hour, minute
    var startDate = IntArray(5)
    var address: String = ""

    var url : String = "https://firebasestorage.googleapis.com/v0/b/weout-582de.appspot.com/o/weout.PNG?alt=media&token=56f74d7e-c23d-4739-b535-4fdd45117296"

    private fun getTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostEventBinding.inflate(inflater, container, false)
        val view = binding.root

        this.setupSpinner()

        binding.publishBttn.setOnClickListener(::publish)

        binding.dateBttn.setOnClickListener{
            getTimeCalendar()
            DatePickerDialog((activity as HomeActivity), this, year, month, day).show()
        }

        this.setupImageGrid()
        return view
    }

    private fun setupImageGrid() {
        binding.imageGrid.grid1.setOnClickListener(::handleImage)
        binding.imageGrid.grid2.setOnClickListener(::handleImage)
        binding.imageGrid.grid3.setOnClickListener(::handleImage)
        binding.imageGrid.grid4.setOnClickListener(::handleImage)
        binding.imageGrid.grid5.setOnClickListener(::handleImage)
        binding.imageGrid.grid6.setOnClickListener(::handleImage)
    }

    private fun setupSpinner(){

        this.states = listOf<String>("Concierto", "Deporte", "Bar", "Comida", "Cultura")
        val adapter =  ArrayAdapter((activity as HomeActivity), android.R.layout.simple_spinner_dropdown_item, states)
        binding.categorySpinner.adapter = adapter

        binding.categorySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                actualState = states[pos]
                Toast.makeText((activity as HomeActivity), states[pos], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun publish(view: View){

        val eventName = binding.eventNameInput.text.toString()
        var eventCategory = this.actualState
        val eventDescription = binding.descriptionInput.text.toString()
        val eventPrice = binding.priceInput.text.toString()
        val minimumAge = binding.ageInput.text.toString()
        val date = binding.dateTextView.text.toString()
        val address = binding.addressInput.text.toString()
        val extraInfo = binding.extraInfoInput.text.toString()


        val sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)

        val user = sharedPreferences.getString("userId", "")

        val coords = ""

       /*val post = user?.let {
            Post(UUID.randomUUID().toString(), it, eventName, eventCategory,
                photoArray, eventDescription, eventPrice.toDouble(), minimumAge.toInt(), date,
                System.currentTimeMillis().toString(), address, extraInfo, coords)
        }*/

        val post: Post? = user?.let { Post(UUID.randomUUID().toString(), it, "Nombre evento", "Deporte",
            photoArray, "Descripción", 10.0, 18, "fecha", System.currentTimeMillis().toString(),
            "dirección", "apto704", "coordenadas") }

        if (post != null) {
            this.makePost(post)
            Toast.makeText((activity as HomeActivity), "Publicación realizada con éxito", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText((activity as HomeActivity), "No pudo realizarse la publicación", Toast.LENGTH_LONG).show()
        }
    }

    private fun uploadImageToFirebase(uri: Uri){
        val fileRef:StorageReference = FirebaseStorage.getInstance().reference.child("${System.currentTimeMillis()}${getFileExtension(uri)}")

        fileRef.putFile(uri).addOnSuccessListener {



            fileRef.downloadUrl.addOnSuccessListener { uri ->
                Toast.makeText(activity as HomeActivity, "Uploaded Successfully!!!", Toast.LENGTH_SHORT).show()



                this.photoArray.add(uri.toString())
            }
        }.addOnFailureListener {
            Toast.makeText(activity as HomeActivity, "Uploading Failed !!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileExtension(uri: Uri): String {

        return ".jpg"
    }

    private fun makePost(post: Post) {
        Firebase.firestore.collection("posts").document(post.id).set(post)
    }

    @SuppressLint("ResourceType")
    private fun handleImage(view: View){
        val img = view as ImageButton
        Toast.makeText((activity as HomeActivity), "Grid id: ${img.id}", Toast.LENGTH_LONG).show()


        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        galleryLauncher.launch(intent)
    }

    fun onGalleryResult(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let{
                if(this.currentImage == "grid1"){
                    binding.imageGrid.grid1.setImageURI(it)
                }else if(this.currentImage == "grid2"){
                    binding.imageGrid.grid2.setImageURI(it)
                }else if(this.currentImage == "grid3"){
                    binding.imageGrid.grid3.setImageURI(it)
                }else if(this.currentImage == "grid4"){
                    binding.imageGrid.grid4.setImageURI(it)
                }else if(this.currentImage == "grid5"){
                    binding.imageGrid.grid5.setImageURI(it)
                }else if(this.currentImage == "grid6"){
                    binding.imageGrid.grid6.setImageURI(it)
                }

                this.uploadImageToFirebase(it)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostEventFragment()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        this.startDate[0] = dayOfMonth
        this.startDate[1] = month
        this.startDate[2] = year

        getTimeCalendar()
        TimePickerDialog(activity, this, hour, minute, true).show()
    }



    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.startDate[3] = hourOfDay
        this.startDate[4] = minute

        binding.dateTextView.text = "${startDate[0]}/${startDate[1]}/${startDate[2]}-${startDate[3]}:${startDate[4]}"
    }
}