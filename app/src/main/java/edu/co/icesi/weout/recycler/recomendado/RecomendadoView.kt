package edu.co.icesi.weout.recycler.recomendado

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class RecomendadoView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageRow: ImageButton = itemView.findViewById(R.id.imageBtnR)
    var tituloRow: TextView = itemView.findViewById(R.id.lugarTV)
    var descRow: TextView = itemView.findViewById(R.id.descripcionTV)
}
