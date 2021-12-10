package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.databinding.ActivityAboutUsBinding
import edu.co.icesi.weout.databinding.ActivityAdvertiseBinding

class AdvertiseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvertiseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvertiseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.backBttnAd.setOnClickListener{
            finish()
        }
    }
}