package com.thakursa.prakriti.utilities

import com.thakursa.prakriti.models.AQIModel
import com.thakursa.prakriti.models.WeatherModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AQIInterface {
    @GET("air_pollution")
    fun getAQI(

        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("APPID") appid:String
    ): Call<AQIModel>


    @GET("air_pollution")
    fun getCityAQI(
        @Query("q") q:String,
        @Query("APPID") appid:String
    ):Call<AQIModel>
}