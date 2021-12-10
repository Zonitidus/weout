package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.databinding.ActivityAboutUsBinding
import edu.co.icesi.weout.databinding.ActivityHomeBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.backBttn.setOnClickListener{
            finish()
        }
    }
}