package edu.co.icesi.weout.notification

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.model.Notification
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.post.PostViewHolder
import edu.co.icesi.weout.recycler.PostView

class NotificationAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var notifications = ArrayList<Notification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PostViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_row, parent, false)
        val postViewHolder = PostViewHolder(view)

        return postViewHolder

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val notification = notifications[position]
        holder.postItemName.text = notification.message

        var url = notification.img

        Glide.with(holder.itemView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.weout)
            .into(holder.postItemImage)

//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, PostInfoActivity::class.java).apply {
//                putExtra("post", post)
//            }
//            ContextCompat.startActivity(it.context, intent, null)
//        }

    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    fun addNotification(notification : Notification) {
        notifications.add(notification)

    }

    fun clear() {
        notifications = ArrayList<Notification>()
    }

}