package edu.co.icesi.weout.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.HomeActivity
import edu.co.icesi.weout.databinding.FragmentPostEventBinding
import edu.co.icesi.weout.model.Post
import java.util.*
import kotlin.collections.ArrayList

class PostEventFragment : Fragment() {

    private var _binding: FragmentPostEventBinding? = null
    private val binding get() = _binding!!

    private lateinit var states: List<String>
    private var actualState: String = ""

    private var selectedCoords: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostEventBinding.inflate(inflater, container, false)
        val view = binding.root

        this.setupSpinner()

        binding.dateBttn.setOnClickListener(::pickDate)
        binding.publishBttn.setOnClickListener(::publish)

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

    private fun pickDate(view: View){

    }

    private fun setupSpinner(){

        this.states = listOf<String>("Cat1", "Cat2", "Cat3")
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

        val user = ""
        val photoArray = ArrayList<String>()
        val coords = ""

        val post = Post(UUID.randomUUID().toString(), user, eventName, eventCategory, 
            photoArray, eventDescription, eventPrice.toDouble(), minimumAge.toInt(), date, 
            System.currentTimeMillis().toString(), address, extraInfo, coords)
    
        this.makePost(post)
    }

    private fun makePost(post: Post) {

    }

    @SuppressLint("ResourceType")
    private fun handleImage(view: View){
        val img = view as ImageButton
        Toast.makeText((activity as HomeActivity), view.id, Toast.LENGTH_LONG).show()
        //img.setImageBitmap()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostEventFragment()
    }
}