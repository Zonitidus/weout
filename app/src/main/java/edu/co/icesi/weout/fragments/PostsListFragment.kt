package edu.co.icesi.weout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.FragmentPostsListBinding
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.post.PostAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [PostsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsListFragment : Fragment() {

    private lateinit var binding : FragmentPostsListBinding

    private val adapter = PostAdapter()

    private lateinit var posts : ArrayList<Post>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPostsListBinding.inflate(inflater, container, false)

        binding.testView.text = "AAAAAAAAAAAAAAAAAAAAAA"

        val recycler = binding.postsRecyclerView

        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter
        recycler.smoothScrollToPosition(0)

        posts = ArrayList()

        val query = Firebase.firestore.collection("posts").get()

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


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}