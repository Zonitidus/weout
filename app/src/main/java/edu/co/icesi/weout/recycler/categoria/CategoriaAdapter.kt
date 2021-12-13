package edu.co.icesi.weout.recycler.categoria

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.model.Post

class CategoriaAdapter : RecyclerView.Adapter<CategoriaView>() {

    private val categorias = ArrayList<Categoria>()
    var categoria : Categoria? = null
    var listener: OnCategoryChanged? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.categoria_row, parent, false)
        val categoriaView = CategoriaView(row)
        return categoriaView
    }


    override fun onBindViewHolder(holder: CategoriaView, position: Int) {
        val category = categorias[position]
        holder.categoria = category
        holder.btn.text = category.categoria
        categoria = category!!



        holder.itemView.setOnClickListener {
            categoria?.let {
                listener?.categoryChange(it)
            }
        }
    }


    interface OnCategoryChanged {
        fun categoryChange(cat : Categoria)
    }


    override fun getItemCount(): Int {
        return categorias.size
    }

    fun addCategory(categoria : Categoria) {
        categorias.add(categoria)
    }



}
