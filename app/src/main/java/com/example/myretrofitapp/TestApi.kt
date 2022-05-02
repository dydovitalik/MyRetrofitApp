package com.example.myretrofitapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestApi {

    @GET("b/626fa3da38be296761fb0e04")
    fun getCurrentCategoryRestaurantMenu(@Header("secret-key") secretKey: String): Call<CategoryRestaurantMenu>
}