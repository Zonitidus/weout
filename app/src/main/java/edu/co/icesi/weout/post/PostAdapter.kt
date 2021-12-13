package edu.co.icesi.weout.post

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.recycler.PostView
import edu.co.icesi.weout.recycler.categoria.Categoria
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var posts = ArrayList<Post>()
    private var postsOriginal = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PostViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_row, parent, false)
        val postViewHolder = PostViewHolder(view)

        postsOriginal.addAll(posts)

        return postViewHolder

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post = posts[position]
        holder.postItemName.text = post.eventName

        var urlArr = post.photos

        if (urlArr.size > 0) {
            Glide.with(holder.itemView)
                .load(urlArr[0])
                .centerCrop()
                .placeholder(R.drawable.weout)
                .into(holder.postItemImage)
        }
        else {
            Glide.with(holder.itemView)
                .load("")
                .centerCrop()
                .placeholder(R.drawable.weout)
                .into(holder.postItemImage)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PostInfoActivity::class.java).apply {
                putExtra("post", post)
            }
            startActivity(it.context, intent, null)
        }



    }



    fun setPost(postss : ArrayList<Post>){
        posts = postss
        notifyDataSetChanged()
        Log.e(">>>", "cargados los post")
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun addPost(post : Post) {
        posts.add(post)

    }

    fun clear() {
        posts = ArrayList<Post>()
    }




}