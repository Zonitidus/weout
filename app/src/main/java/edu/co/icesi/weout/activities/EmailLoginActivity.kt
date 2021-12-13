package edu.co.icesi.weout.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import edu.co.icesi.weout.databinding.ActivityEmailLoginBinding
import edu.co.icesi.weout.model.User

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

            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {

                val fbuser = Firebase.auth.currentUser
                if(fbuser!!.isEmailVerified){

                    //Le damos acceso

                    //Pedimos el usuario almacenado en la bd
                    Firebase.firestore.collection("users").document(fbuser.uid).get().addOnSuccessListener {
                        val user = it.toObject(User::class.java)
                        //Guardamos el usuario en shared preferencies
                        saveUser(user!!, ProviderType.BASIC)
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                        LoginActivity().finish()
                    }

                }else{
                    Toast.makeText(this, "Su email no está verificado", Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }

        }

    }

    fun saveUser(user : User, provider : ProviderType){
        val sp = getSharedPreferences("user", MODE_PRIVATE)
        sp.edit().putString("userId", user.id).apply()
        sp.edit().putString("provider", provider.name).apply()
        sp.edit().putString("email", user.email).apply()
    }

}