package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.weout.R
import edu.co.icesi.weout.model.Post

class LikedPostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked_posts)

        val userId = intent.extras?.get("userid") as String

    }
}