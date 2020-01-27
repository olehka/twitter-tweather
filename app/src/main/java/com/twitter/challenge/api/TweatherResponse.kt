package com.twitter.challenge.api


import com.google.gson.annotations.SerializedName
import com.twitter.challenge.api.info.*

data class TweatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
    @SerializedName("weather")
    val weatherInfo: WeatherInfo? = null,
    @SerializedName("wind")
    val windInfo: WindInfo? = null,
    @SerializedName("rain")
    val rainInfo: RainInfo? = null,
    @SerializedName("clouds")
    val cloudsInfo: CloudsInfo? = null,
    @SerializedName("name")
    val cityName: String? = null
)