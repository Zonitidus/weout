package edu.co.icesi.weout.recycler.recomendado

import android.media.Image
import android.widget.ImageButton

class Recomendado {
    var imagen: ImageButton
    var lugar: String
    var desc: String

    constructor(imagen: ImageButton, lugar: String, desc: String) {
        this.imagen = imagen
        this.lugar = lugar
        this.desc = desc
    }
}