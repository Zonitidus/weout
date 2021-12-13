package edu.co.icesi.weout.activities

import com.facebook.appevents.AppEventsLogger;

import com.google.firebase.auth.FirebaseUser
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookException
import com.facebook.FacebookCallback
import com.facebook.AccessToken
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityLoginBinding
import edu.co.icesi.weout.model.User

open class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callbackManager = CallbackManager.Factory.create()

        auth = Firebase.auth

        //Nuevo
        FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
//////////////////////////////////////////////////////////////7
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.emailLoginBtn.setOnClickListener {

            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
            //finish()

        }

        binding.loginButton.setOnClickListener {
            //LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))

            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult>{

                override fun onSuccess(result: LoginResult) {

                    Log.d(TAG, "facebook:onSuccess:$result")
                    handleFacebookAccessToken(result.accessToken)


                    result?.let {
                        val token = it.accessToken

                        val credential = FacebookAuthProvider.getCredential(token.token)

                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                            if (it.isSuccessful){
                                showHome(it.result?.user?.email  ?: "", ProviderType.FACEBOOK)
                                finish()
                            }else{
                                showAlert(3)
                            }

                        }

                    }

                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")

                }

                override fun onError(error: FacebookException) {
                    Log.e(">>>", "ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                    Log.e(TAG, "facebook:onError", error)

                    showAlert(1)
                }

            })
        }

        /*binding.facebookLoginBtn.setOnClickListener {
            //val intent = Intent(this, FacebookAuthActivity::class.java)
            //startActivity(intent)
            //finish()
            //LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$result")
                    handleFacebookAccessToken(result.accessToken)
                    result?.let {
                        val token = it.accessToken
                        val credential = FacebookAuthProvider.getCredential(token.token)
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                            if (it.isSuccessful){
                                showHome(it.result?.user?.email  ?: "", ProviderType.FACEBOOK)
                            }else{
                                showAlert(4)
                            }
                        }
                    }
                }
                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }
                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                    showAlert(2)
                }
            })
        }*/

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            //finish()
        }

    }

    private fun showAlert(number : Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")

        builder.setMessage("${number}. Se ha producido un error autenticando al usuario")

        builder.setMessage("1. Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {



        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun showHome(email:String, provider : ProviderType){

        val homeIntent = Intent(this, HomeActivity::class.java)

        val sp = getSharedPreferences("user", MODE_PRIVATE)
        sp.edit().putString("provider", provider.name).apply()
        sp.edit().putString("email", email).apply()

        startActivity(homeIntent)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    companion object {
        private const val TAG = "FacebookLogin"
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

}
