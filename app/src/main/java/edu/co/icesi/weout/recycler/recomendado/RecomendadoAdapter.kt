package edu.co.icesi.weout.recycler.recomendado

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class RecomendadoAdapter : RecyclerView.Adapter<RecomendadoView>() {

    private val recomendados = ArrayList<Recomendado>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendadoView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.recomendado_row, parent, false)
        val recomendadoView = RecomendadoView(row)
        return recomendadoView
    }

    override fun onBindViewHolder(holder: RecomendadoView, position: Int) {
        val recomendado = recomendados[position]
        holder.tituloRow.text = recomendado.lugar
        holder.descRow.text = recomendado.desc
        holder.imageRow = recomendado.imagen
    }

    override fun getItemCount(): Int {
        return recomendados.size
    }

}
