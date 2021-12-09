package edu.co.icesi.weout.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityHomeBinding
import edu.co.icesi.weout.fragments.PostEventFragment
import edu.co.icesi.weout.fragments.PostsListFragment

enum class ProviderType{
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var postEventFragment: PostEventFragment
    private lateinit var postsListFragment: PostsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.postEventFragment  = PostEventFragment.newInstance()
        this.postsListFragment = PostsListFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
                menuItem ->
            if(menuItem.itemId == R.id.publishItem) {
                showFragment(postEventFragment)
            }
            if(menuItem.itemId == R.id.homeItem) {
                showFragment(postsListFragment)
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
}