package edu.co.icesi.weout.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.databinding.FragmentPostsListBinding
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.post.PostAdapter
import edu.co.icesi.weout.recycler.categoria.Categoria
import edu.co.icesi.weout.recycler.categoria.CategoriaAdapter
import kotlinx.android.synthetic.main.fragment_posts_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [PostsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsListFragment : Fragment(), CategoriaAdapter.OnCategoryChanged {

    private lateinit var binding : FragmentPostsListBinding

    private var cat = "todos"

    private val adapter = PostAdapter()
    private val categoryAdapter = CategoriaAdapter()

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


        val categoryRecycler = binding.categoriasRecycler
        val recycler = binding.postsRecyclerView
        val search = binding.search
        categoryAdapter.listener = this


        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter
        recycler.smoothScrollToPosition(0)


        categoryRecycler.setHasFixedSize(false)
        categoryRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = categoryAdapter
        categoryRecycler.smoothScrollToPosition(0)



        val catQuery = Firebase.firestore.collection("category").get()
        catQuery.addOnCompleteListener {
            if (categoryAdapter.itemCount == 0) {
                for (document in it.result!!) {
                    var categ = document.toObject(Categoria::class.java)
                    categoryAdapter.addCategory(categ)
                    categoryAdapter.notifyDataSetChanged()
                }
            }
        }

        posts = ArrayList()



        //TODO observer??? como actualizo la categoria desde Categoria Adapter, help, tengo sueño :C
        var query = Firebase.firestore.collection("posts").get()
        if (cat != "todos") {
            query = Firebase.firestore.collection("posts").whereEqualTo("category", cat.lowercase()).get()
        }



        query.addOnCompleteListener {

            if (it.result?.size() != 0) {
                Toast.makeText(getContext(), "si hay eventos con la categoriaaaaa " + cat, Toast.LENGTH_LONG).show()
                adapter.clear()

                for (document in it.result!!) {

                    var post = document.toObject(Post::class.java)
                    adapter.addPost(post)
                    adapter.notifyDataSetChanged()

                }

            } else {
                Toast.makeText(getContext(), "No hay eventos con la categoria " + cat, Toast.LENGTH_LONG).show()
            }

        }

        binding.srchBtn.setOnClickListener{
            val s = binding.search.text.toString()
            var postss : ArrayList<Post> = ArrayList()
            var querya = Firebase.firestore.collection("posts").get()
            if (s != "") {
                querya = Firebase.firestore.collection("posts").whereEqualTo("eventName", s).get()
                Log.e(">>>", s)
            }

            querya.addOnCompleteListener { task->
                if(task.result?.size() != 0) {
                    adapter.clear()
                    for (document in task.result!!) {
                        val temp: Post = document.toObject(Post::class.java)
                        adapter.addPost(temp)
                        adapter.notifyDataSetChanged()
                        Log.e(">>>", "name: " + temp.eventName)
                    }
                }
            }
            adapter.setPost(postss)



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

    override fun categoryChange(cat: Categoria) {
        this.cat = cat.categoria
        cargarXCategoria()
    }

    fun cargarXCategoria() {
        var query = Firebase.firestore.collection("posts").get()
        if (cat != "todos") {
            query = Firebase.firestore.collection("posts").whereEqualTo("category", cat.lowercase()).get()
        }
        query.addOnCompleteListener {

            if (it.result?.size() != 0) {
                Toast.makeText(getContext(), cat, Toast.LENGTH_SHORT).show()
                adapter.clear()

                for (document in it.result!!) {

                    var post = document.toObject(Post::class.java)
                    adapter.addPost(post)
                    adapter.notifyDataSetChanged()

                }

            } else {
                Toast.makeText(getContext(), "No hay eventos con la categoria " + cat, Toast.LENGTH_SHORT).show()
            }

        }
    }


}