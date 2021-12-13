package edu.co.icesi.weout.recycler.categoria

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class CategoriaView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var btn: TextView = itemView.findViewById(R.id.button)
    var categoria : Categoria? = null

}
