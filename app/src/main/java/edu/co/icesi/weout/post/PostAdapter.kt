package edu.co.icesi.weout.post

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.recycler.PostView

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PostViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_row, parent, false)
        val postViewHolder = PostViewHolder(view)

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