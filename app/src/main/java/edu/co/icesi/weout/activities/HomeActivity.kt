package edu.co.icesi.weout.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityHomeBinding
import edu.co.icesi.weout.fragments.PostEventFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var postEventFragment: PostEventFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.postEventFragment  = PostEventFragment.newInstance()

        val sharedPreferences = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putString("user", "siteName")
            putString("userId", "imageUri")
        }.apply()


        binding.bottomNavigationView.setOnItemSelectedListener {
                menuItem ->
            if(menuItem.itemId == R.id.publishItem) {
                showFragment(postEventFragment)
            }
            true
        }
        //this.showFragment(homefragment)
    }

    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}