package edu.co.icesi.weout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.databinding.FragmentPostsListBinding
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.post.PostAdapter
import edu.co.icesi.weout.recycler.categoria.Categoria
import edu.co.icesi.weout.recycler.categoria.CategoriaAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [PostsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsListFragment : Fragment(),  SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentPostsListBinding

    private var cat = "Todos"

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

        search.setOnQueryTextListener(this)

        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter
        recycler.smoothScrollToPosition(0)


        categoryRecycler.setHasFixedSize(false)
        categoryRecycler.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = adapter
        categoryRecycler.smoothScrollToPosition(0)


        //TODO me esta cargando los eventos en vez de las categorias aaaaaaaaaaaa
        val catQuery = Firebase.firestore.collection("category").get()
        catQuery.addOnCompleteListener {
            if (categoryAdapter.itemCount == 0) {
                for (document in it.result!!) {
                    categoryAdapter.addCategory(document.toObject(Categoria::class.java))
                    categoryAdapter.notifyDataSetChanged()
                }
            }

        }

        posts = ArrayList()



        //TODO observer??? como actualizo la categoria desde Categoria Adapter, help, tengo sueño :C
        var query = Firebase.firestore.collection("posts").get()
        if (cat != "Todos") {
            query = Firebase.firestore.collection("posts").whereEqualTo("category", cat).get()
        }



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

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String?): Boolean {
        if (s != null) {
            adapter.filtrado(s)
        }
        return false
    }
}