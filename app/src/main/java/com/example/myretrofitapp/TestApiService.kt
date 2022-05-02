package com.example.myretrofitapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TestApiService {

    companion object {
        const val SECRET_KEY = "\$2b\$10\$0lBPy9LoWFzXksFcyju8cemLFcJKIJwcngqUBYcERr3b0f5.kPEbS"
    }

    var api: TestApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TestApi::class.java)
    }

    fun getCurrentCategoryRestaurantMenu(callback: RestaurantMenuCallback) {
        api.getCurrentCategoryRestaurantMenu(SECRET_KEY).enqueue(object : Callback<CategoryRestaurantMenu>{
            override fun onResponse( call: Call<CategoryRestaurantMenu>, response: Response<CategoryRestaurantMenu>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }

            override fun onFailure(call: Call<CategoryRestaurantMenu>, t: Throwable) {
                    callback.onFailure()
            }
        })
    }

    interface RestaurantMenuCallback{
        fun onSuccess(categoryRestaurantMenu: CategoryRestaurantMenu)
        fun onFailure()
    }
}