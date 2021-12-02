package edu.co.icesi.weout.recycler.categoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R

class CategoriaAdapter : RecyclerView.Adapter<CategoriaView>() {

    private val categorias = ArrayList<Categoria>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.categoria_row, parent, false)
        val categoriaView = CategoriaView(row)
        return categoriaView
    }


    override fun onBindViewHolder(holder: CategoriaView, position: Int) {
        val category = categorias[position]

        holder.btn = category.category
    }


    override fun getItemCount(): Int {
        return categorias.size
    }

}
