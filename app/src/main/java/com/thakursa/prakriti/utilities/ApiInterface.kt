package com.thakursa.prakriti.utilities

import com.thakursa.prakriti.models.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getCurrentWeatherDta(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("APPID") appid:String
    ):Call<WeatherModel>



    @GET("weather")
    fun getCityWeatherData(
        @Query("q") q:String,
        @Query("APPID") appid:String
    ):Call<WeatherModel>
}