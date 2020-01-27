package com.twitter.challenge.api.info


import com.google.gson.annotations.SerializedName

data class WindInfo(
    @SerializedName("speed")
    val speed: Double? = null,
    @SerializedName("deg")
    val degrees: Int? = null
)