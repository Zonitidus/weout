package edu.co.icesi.weout.recycler.categoria

import android.widget.Button
import java.io.Serializable

data class Categoria (
    var categoria: String = "",
) : Serializable {
    override fun toString(): String {
        return categoria
    }
}


