package edu.co.icesi.weout.recycler.promociones

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class PromocionesView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageRow: ImageButton = itemView.findViewById(R.id.imagenBtnP)
}
