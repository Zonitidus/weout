package edu.co.icesi.weout.model

import java.io.Serializable

data class Notification(
    var id : String = "",
    var message : String = "",
    var img : String = "",
    var time : Long = 0

) : Serializable