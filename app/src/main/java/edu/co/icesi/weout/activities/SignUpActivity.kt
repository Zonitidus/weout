package edu.co.icesi.weout.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivitySignUpBinding
import edu.co.icesi.weout.model.User
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private var userGender : String = "null"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.signUpBtn.setOnClickListener(::register)

        //Estos métodos son para cambiar el color de un boton al ser seleccionado
        binding.femaleGenderBtn.setOnClickListener {
            this.userGender = binding.femaleGenderBtn.text.toString()
            binding.maleGenderBtn.setBackgroundColor(R.color.light_purple)
            binding.femaleGenderBtn.setBackgroundColor(R.color.orange)
            binding.otherGenderBtn.setBackgroundColor(R.color.light_purple)
        }

        binding.maleGenderBtn.setOnClickListener{
            this.userGender = binding.maleGenderBtn.text.toString()
            binding.maleGenderBtn.setBackgroundColor(R.color.orange)
            binding.femaleGenderBtn.setBackgroundColor(R.color.light_purple)
            binding.otherGenderBtn.setBackgroundColor(R.color.light_purple)
        }

        binding.otherGenderBtn.setOnClickListener{
            this.userGender = binding.otherGenderBtn.text.toString()
            binding.maleGenderBtn.setBackgroundColor(R.color.light_purple)
            binding.femaleGenderBtn.setBackgroundColor(R.color.light_purple)
            binding.otherGenderBtn.setBackgroundColor(R.color.orange)
        }
    }

    private fun register(view : View){
        var password1 = binding.passwordSignupET.text.toString()
        var password2 = binding.passwordConfirmET.text.toString()

        if(password1 != password2){
            Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_LONG).show()
        }else{
            if(userGender.equals("null")){
                Toast.makeText(this, "Por favor, escoge un género", Toast.LENGTH_LONG).show()
            }else{
                //1. Registrar usuario en la db
                Firebase.auth.createUserWithEmailAndPassword(

                    binding.emailSignupET.text.toString(),
                    binding.passwordSignupET.text.toString()

                ).addOnSuccessListener {
                    //2. Registrar todos los datos

                    val id = Firebase.auth.currentUser?.uid

                    /*var birthDate : Long = 0

                    var birthDateString = binding.birthDayET.text.toString()

                    var format1 = SimpleDateFormat("dd-MM-yyyy")

                    var date = format1.parse(birthDateString)*/

                    val user = User(
                        id!!,
                        binding.nameET.text.toString(),
                        binding.lastNameET.text.toString(),
                        binding.emailSignupET.text.toString(),
                        binding.birthDayET.text.toString(),
                        userGender,
                        binding.phoneET.text.toString()
                    )

                    Firebase.firestore.collection("users").document(id).set(user).addOnSuccessListener {
                        sendVerificationEmail()
                        startActivity(Intent(this, EmailLoginActivity::class.java))
                        finish()                }

                }.addOnFailureListener{
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }



    }

    fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this, "Verifica tu email antes de entrar", Toast.LENGTH_LONG).show()
        }?.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }

}