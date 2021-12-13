package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityLikedPostsBinding
import edu.co.icesi.weout.databinding.ActivityMyPostListBinding
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.post.PostAdapter

class MyPostListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyPostListBinding

    private val adapter = PostAdapter()

    private lateinit var posts : ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.extras?.get("userid") as String

        val recycler = binding.myPostsRV

        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        recycler.smoothScrollToPosition(0)

        posts = ArrayList()

        val query = Firebase.firestore.collection("posts").whereEqualTo("user", userId).get()

        query.addOnCompleteListener {

            if (it.result?.size() != 0) {
                adapter.clear()

                for (document in it.result!!) {
                    var post = document.toObject(Post::class.java)
                    adapter.addPost(post)
                    adapter.notifyDataSetChanged()
                }
            }

        }

    }
}