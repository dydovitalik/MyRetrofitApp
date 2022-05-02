package com.example.myretrofitapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryRestaurantMenu {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("menus")
    @Expose
    var menus: Menus? = null
}