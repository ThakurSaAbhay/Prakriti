package com.thakursa.prakriti.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AQIUtilities {

    private var retrofit: Retrofit?=null
    var BASE_URL="http://api.openweathermap.org/data/2.5/air_pollution/"


    fun getAQIInterface(): AQIInterface? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

        }
        return retrofit?.create(AQIInterface::class.java)
    }

}
