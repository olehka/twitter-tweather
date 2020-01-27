package com.twitter.challenge.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TweatherService {

    companion object {
        const val BASE_URL = "https://twitter-code-challenge.s3.amazonaws.com/"
    }

    @GET("current.json")
    suspend fun getCurrentWeather(): Response<TweatherResponse>

    @GET("future_{day}.json")
    suspend fun getFutureWeather(@Path("day") day: Int): Response<TweatherResponse>
}