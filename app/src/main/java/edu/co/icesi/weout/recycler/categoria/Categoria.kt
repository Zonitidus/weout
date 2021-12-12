package edu.co.icesi.weout.recycler.categoria

import android.widget.Button
import java.io.Serializable

data class Categoria (
    var category: String = "",
) : Serializable {
    override fun toString(): String {
        return category
    }
}


