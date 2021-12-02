package edu.co.icesi.weout.recycler.promociones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class PromocionesAdapter : RecyclerView.Adapter<PromocionesView>() {

    private val promociones = ArrayList<Promocion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionesView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.promocion_row, parent, false)
        val promocionesView = PromocionesView(row)
        return promocionesView
    }

    override fun onBindViewHolder(holder: PromocionesView, position: Int) {
        val promo = promociones[position]
        holder.imageRow = promo.promo
    }

    override fun getItemCount(): Int {
        return promociones.size
    }

}
