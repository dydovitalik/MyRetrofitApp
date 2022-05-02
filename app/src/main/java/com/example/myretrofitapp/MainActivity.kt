package com.example.myretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    lateinit var thumbImage1: ImageView
    lateinit var tvCategoryName: TextView

    lateinit var thumbImage2: ImageView
    lateinit var dishName: TextView
    lateinit var dishPrice: TextView
    lateinit var dishWeight: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_category_row)

        initView()
    }

    private fun initView() {
        thumbImage1 = findViewById(R.id.thumbImage1)
        tvCategoryName = findViewById(R.id.tvCategoryName)
        thumbImage2 = findViewById(R.id.thumbImage2)
        dishName = findViewById(R.id.DishName)
        dishPrice = findViewById(R.id.DishPrice)
        dishWeight = findViewById(R.id.DishWeight)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        Log.d("API", "loadData")
        val service = TestApiService()
        service.getCurrentCategoryRestaurantMenu(object : TestApiService.RestaurantMenuCallback{

            override fun onSuccess(categoryRestaurantMenu: CategoryRestaurantMenu){
                displaycategoryRestaurantMenu(categoryRestaurantMenu)
            }

            override fun onFailure(){
                displayError()
            }
        })
    }

    private fun displayError(){
        Log.d("API","loading data error")
        Toast.makeText(MainActivity@ this, "Failed to load data", Toast.LENGTH_LONG).show()
    }

    private fun displaycategoryRestaurantMenu(categoryRestaurantMenu: CategoryRestaurantMenu){
        Log.d("API", "${categoryRestaurantMenu.image}")
        Log.d("API", "${categoryRestaurantMenu.name}")
        Log.d("API", "${categoryRestaurantMenu.menus?.name}")
        Log.d("API", "${categoryRestaurantMenu.menus?.price}")
        Log.d("API", "${categoryRestaurantMenu.menus?.weight}")
        Log.d("API", "${categoryRestaurantMenu.menus?.url}")

        Glide.with(thumbImage1).load(categoryRestaurantMenu.image).into(thumbImage1)
        tvCategoryName.setText("Category : ${categoryRestaurantMenu.name}")
        Glide.with(thumbImage2).load(categoryRestaurantMenu.menus?.url).into(thumbImage2)
        dishName.setText("Dish : ${categoryRestaurantMenu.menus?.name!!}" )
        dishPrice.setText("Price : ${categoryRestaurantMenu.menus?.price!!}" )
        dishWeight.setText("Weight : ${categoryRestaurantMenu.menus?.weight}" )
    }
}