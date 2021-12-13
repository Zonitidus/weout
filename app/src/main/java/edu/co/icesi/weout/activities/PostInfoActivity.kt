package edu.co.icesi.weout.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityPostInfoBinding
import edu.co.icesi.weout.model.Notification
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.model.User

class PostInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_post_info)

        binding = ActivityPostInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.extras?.get("post") as Post


        if (post.photos.size > 0) {
            Glide.with(binding.root)
                .load(post.photos[0])
                .centerCrop()
                .placeholder(0)
                .into(binding.imagenInfo)
        }


        binding.categoryPostTV.text = post.category.toString()
        binding.descripcionPostTV.text = post.description.toString()
        binding.direccionPostTV.text = post.address.toString()
        binding.edadPostTV.text = post.age.toString()
        binding.fechaPostTV.text = post.eventDate.toString()
        //binding.gastoPostTV.text = post.
        var price = binding.precioPostTV.text.toString().toDouble()
        if (price <= 10000) {
            binding.gastoPostTV.text = "$"
        } else if (price <= 20000) {
            binding.gastoPostTV.text = "$$"
        } else if (price <= 30000) {
            binding.gastoPostTV.text = "$$$"
        } else {
            binding.gastoPostTV.text = "$$$$"
        }
        //binding.numeroPostTV.text = post.
        //binding.organizadorPostTV.text = post.
        binding.precioPostTV.text = post.price.toString()

        binding.mapRedirectBttn.setOnClickListener{

            val coords = post.coords.split(",").toTypedArray()

            val lat = coords[0]
            val lng = coords[1]

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${lat},${lng}&mode=d"))
            intent.setPackage("com.google.android.apps.maps")

            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        binding.volver1Btn.setOnClickListener{
            finish()
        }

        binding.likeBtn.setOnClickListener {

            Toast.makeText(applicationContext, "You have liked this post!", Toast.LENGTH_SHORT).show()

            val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
            val userId = sharedPreferences.getString("userId", "")
            val authorId = post.user

            var imgURL = ""

            val query = Firebase.firestore.collection("users").document(userId!!).get()
            var user : User? = null

            if (post.photos.size > 0) {
                imgURL = post.photos[0]
            }

            query.addOnCompleteListener {
                user = it.result!!.toObject(User::class.java)

                val notificationUser = Notification("${post.id}", "Le has dado me gusta a ${post.eventName}", imgURL, System.currentTimeMillis())

                val notificationAuthor = Notification("${userId}:${post.id}",
                    "A ${user!!.name} ${user!!.lastName} le gusto tu publicacion ${post.eventName}",
                    "https://firebasestorage.googleapis.com/v0/b/weout-582de.appspot.com/o/heart.png?alt=media&token=ad7f7a30-5fa5-4323-9669-69222ef53cb9",
                    System.currentTimeMillis())

                Firebase.firestore.collection("users").document(userId!!)
                    .collection("likedposts").document(post.id).set(post)

                Firebase.firestore.collection("users").document(authorId).collection("notifications")
                    .document(notificationAuthor.id).set(notificationAuthor)

                Firebase.firestore.collection("users").document(userId).collection("notifications")
                    .document(notificationUser.id).set(notificationUser)

            }



        }


    }
}