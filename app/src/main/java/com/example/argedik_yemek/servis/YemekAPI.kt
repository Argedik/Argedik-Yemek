package com.example.argedik_yemek.servis

import com.example.argedik_yemek.model.Yemek
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface YemekAPI {
    @GET("recipes/quickAnswer?q=How%20much%20vitamin%20c%20is%20in%202%20apples%3F")
    fun getYemek(): Single<List<Yemek>>


}

class YemekAPIServis{
    private val BASE_URL ="https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(YemekAPI::class.java)

    fun getData():Single<List<Yemek>>{
        return api.getYemek()
    }
}