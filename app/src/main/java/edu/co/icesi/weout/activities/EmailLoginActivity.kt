package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {

    private var _binding : ActivityEmailLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEmailLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

        }

    }
}