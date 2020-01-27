package com.twitter.challenge.api.info


import com.google.gson.annotations.SerializedName

data class CloudsInfo(
    @SerializedName("cloudiness")
    val cloudiness: Int? = null
)