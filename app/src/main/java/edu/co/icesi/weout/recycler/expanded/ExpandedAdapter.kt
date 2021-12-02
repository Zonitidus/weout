package edu.co.icesi.weout.recycler.expanded

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class ExpandedAdapter : RecyclerView.Adapter<ExpandedView>() {

    private val expands = ArrayList<Expanded>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandedView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.expanded_row, parent, false)
        val expandedView = ExpandedView(row)
        return expandedView
    }

    override fun onBindViewHolder(holder: ExpandedView, position: Int) {
        val expand = expands[position]
        holder.titulo.text = expand.titulo

        holder.fecha1.text = expand.fecha1
        holder.lugar1.text = expand.lugar1
        holder.imageBtn1 = expand.imagen1

        holder.fecha2.text = expand.fecha2
        holder.lugar2.text = expand.lugar2
        holder.imageBtn2 = expand.imagen2

        holder.fecha1.text = expand.fecha3
        holder.lugar1.text = expand.lugar3
        holder.imageBtn1 = expand.imagen3

        holder.fecha1.text = expand.fecha4
        holder.lugar1.text = expand.lugar4
        holder.imageBtn1 = expand.imagen4
    }

    override fun getItemCount(): Int {
        return expands.size
    }


}
