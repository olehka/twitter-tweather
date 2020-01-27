package com.twitter.challenge.data

import android.util.Log
import com.twitter.challenge.api.TweatherResponse
import com.twitter.challenge.api.TweatherService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class TweatherRepository(
    private val service: TweatherService
) {

    suspend fun getCurrentWeather(): Result<Weather> {
        return when (val result = getResult()) {
            is Result.Success -> {
                result.data.let {
                    val weather = Weather(
                        temperature = it.weatherInfo?.temperature,
                        windSpeed = it.windInfo?.speed,
                        cloudiness = it.cloudsInfo?.cloudiness
                    )
                    Result.Success(weather)
                }
            }
            is Result.Error -> {
                result
            }
        }
    }

    suspend fun getFutureWeather(days: Int = 5): Result<List<Weather>> {
        return when (val result = getResultConcurrent(days)) {
            is Result.Success -> {
                val weatherList = result.data.map {
                    Weather(
                        temperature = it.weatherInfo?.temperature,
                        windSpeed = it.windInfo?.speed,
                        cloudiness = it.cloudsInfo?.cloudiness
                    )
                }
                Result.Success(weatherList)
            }
            is Result.Error -> {
                result
            }
        }
    }

    private suspend fun getResult(): Result<TweatherResponse> {
        try {
            val response = service.getCurrentWeather()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            val errorStr = "Network call has failed: " +
                    "code = ${response.code()}, message = ${response.message()}"
            Log.e("TweatherRepository", errorStr)
            return Result.Error(Exception(errorStr))
        } catch (e: Exception) {
            Log.e("TweatherRepository", e.message, e)
            return Result.Error(e)
        }
    }

    private suspend fun getResultConcurrent(count: Int): Result<List<TweatherResponse>> =
        coroutineScope {
            try {
                val deferreds: List<Deferred<TweatherResponse>> = (1..count).map {
                    async {
                        val response = service.getFutureWeather(it)
                        if (!response.isSuccessful || response.body() == null) {
                            throw Exception(
                                "Network call has failed: " +
                                        "code = ${response.code()}, message = ${response.message()}"
                            )
                        }
                        response.body()!!
                    }
                }
                Result.Success(deferreds.awaitAll())
            } catch (e: Exception) {
                Log.e("TweatherRepository", e.message, e)
                Result.Error(e)
            }
        }
}