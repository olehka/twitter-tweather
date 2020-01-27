package com.twitter.challenge.api.info


import com.google.gson.annotations.SerializedName

data class RainInfo(
    @SerializedName("3h")
    val volume: Int? = null
)