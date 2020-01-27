package com.twitter.challenge.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TweatherClient {

    val tweatherService: TweatherService by lazy {
        Retrofit.Builder()
            .baseUrl(TweatherService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TweatherService::class.java)
    }
}