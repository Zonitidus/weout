package edu.co.icesi.weout.recycler.expanded

import android.media.Image
import android.widget.ImageButton

class Expanded {
    var titulo: String

    var lugar1: String
    var fecha1: String
    var imagen1: ImageButton

    var lugar2: String
    var fecha2: String
    var imagen2: ImageButton

    var lugar3: String
    var fecha3: String
    var imagen3: ImageButton

    var lugar4: String
    var fecha4: String
    var imagen4: ImageButton

    constructor(titulo: String,
                lugar1: String, fecha1: String, imagen1: ImageButton,
                lugar2: String, fecha2: String, imagen2: ImageButton,
                lugar3: String, fecha3: String, imagen3: ImageButton,
                lugar4: String, fecha4: String, imagen4: ImageButton){
        this.titulo = titulo

        this.lugar1 = lugar1
        this.fecha1 = fecha1
        this.imagen1 = imagen1

        this.lugar2 = lugar2
        this.fecha2 = fecha2
        this.imagen2 = imagen2

        this.lugar3 = lugar3
        this.fecha3 = fecha3
        this.imagen3 = imagen3

        this.lugar4 = lugar4
        this.fecha4 = fecha4
        this.imagen4 = imagen4
    }
}