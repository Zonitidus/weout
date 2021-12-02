package edu.co.icesi.weout.post

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var postItemName : TextView = itemView.findViewById(R.id.postRowName)
    var postItemImage : ImageView = itemView.findViewById(R.id.postRowImg)

}