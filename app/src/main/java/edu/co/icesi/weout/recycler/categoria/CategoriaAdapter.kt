package edu.co.icesi.weout.recycler.categoria

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.model.Post

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

        holder.btn.text = category.category

        holder.itemView.setOnClickListener {
            //TODO como mando la info? ???
        }
    }


    override fun getItemCount(): Int {
        return categorias.size
    }

    fun addCategory(categoria : Categoria) {
        categorias.add(categoria)
    }



}
