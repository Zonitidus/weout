package edu.co.icesi.weout.model

import java.io.Serializable

data class Post (

    var id: String = "",
    var user: String = "",
    var eventName: String = "",
    var category: String = "",
    var photos: ArrayList<String> = ArrayList<String>(),
    var description: String = "",
    var price: Double = 0.0,
    var age: Int = 0,
    var eventDate: String = "",
    var postDate: String = "",
    var address: String = "",
    var extraInfo: String = "",
    var coords: String = "",
) : Serializable {
    override fun toString(): String {
        return eventName
    }
}