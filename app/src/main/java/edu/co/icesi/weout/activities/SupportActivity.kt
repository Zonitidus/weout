package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.databinding.ActivityAboutUsBinding
import edu.co.icesi.weout.databinding.ActivitySupportBinding

class SupportActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.backBttnSupp.setOnClickListener{
            finish()
        }

    }
}