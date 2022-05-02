package com.example.myretrofitapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Menus {
    @SerializedName("name")
    @Expose
    var name: String? = null


    @SerializedName("price")
    @Expose
    var price: Float? = null

    @SerializedName("weight")
    @Expose
    var weight: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}