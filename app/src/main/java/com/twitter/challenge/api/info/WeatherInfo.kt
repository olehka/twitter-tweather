package com.twitter.challenge.api.info


import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("temp")
    val temperature: Double? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null
)