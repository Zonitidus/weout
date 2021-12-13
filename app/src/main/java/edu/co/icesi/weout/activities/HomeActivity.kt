package edu.co.icesi.weout.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityHomeBinding

import edu.co.icesi.weout.fragments.NotificationFragment
import edu.co.icesi.weout.fragments.PerfilFragment

import edu.co.icesi.weout.fragments.OtherFragment

import edu.co.icesi.weout.fragments.PostEventFragment
import edu.co.icesi.weout.fragments.PostsListFragment


enum class ProviderType{
    FACEBOOK,
    BASIC
}

class HomeActivity : AppCompatActivity() {



    private lateinit var binding: ActivityHomeBinding
    private lateinit var postEventFragment: PostEventFragment
    private lateinit var postsListFragment: PostsListFragment

    private lateinit var notificationFragment : NotificationFragment
    private lateinit var perfilFragment: PerfilFragment

    private lateinit var otherFragment : OtherFragment
    private var userEmail : String? = null
    private lateinit var userProvider : ProviderType


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.postEventFragment  = PostEventFragment.newInstance()
        this.postsListFragment = PostsListFragment()

        this.notificationFragment = NotificationFragment()
        this.perfilFragment = PerfilFragment()

        this.otherFragment = OtherFragment()

        this.loadUser()
        if(this.userEmail == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }else{
            Toast.makeText(this, "Hola ${userEmail}", Toast.LENGTH_LONG).show()
        }


        binding.bottomNavigationView.setOnItemSelectedListener {
                menuItem ->
            if (menuItem.itemId == R.id.publishItem) {
                showFragment(postEventFragment)
            }
            if (menuItem.itemId == R.id.homeItem) {
                showFragment(postsListFragment)
            }
            if (menuItem.itemId == R.id.notificationItem) {
                showFragment(notificationFragment)
            }
            if (menuItem.itemId == R.id.accountItem) {
                showFragment(perfilFragment)
            }

            if (menuItem.itemId == R.id.otherItem){
                showFragment(otherFragment)
            }

            true
        }
        this.showFragment(postsListFragment)
    }

    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    fun getUserEmail():String?{
        return this.userEmail
    }

    fun getUserProvider():ProviderType{
        return this.userProvider
    }

    fun loadUser(){

        val sp = getSharedPreferences("user", MODE_PRIVATE)
        this.userEmail = sp.getString("email", null)
        val provider = sp.getString("provider", "non")

        if (provider.equals(ProviderType.BASIC.name)){
            this.userProvider = ProviderType.BASIC
        }else if (provider.equals(ProviderType.FACEBOOK.name)){
                this.userProvider = ProviderType.FACEBOOK
            }

    }

}