package com.example.argedik_yemek.servis

import com.example.argedik_yemek.model.Yemek
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface YemekAPI {
    @GET("Argedik/Argedik-Yemek/master/YemekJson.json")
    fun getYemek(): Single<List<Yemek>>


}

