package edu.co.icesi.weout.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityPostInfoBinding
import edu.co.icesi.weout.model.Post

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


    }
}