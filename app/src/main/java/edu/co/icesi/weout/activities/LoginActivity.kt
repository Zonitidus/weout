package edu.co.icesi.weout.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityLoginBinding

open class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.emailLoginBtn.setOnClickListener {

            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding.facebookLoginBtn.setOnClickListener {
            val intent = Intent(this, FacebookAuthActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}