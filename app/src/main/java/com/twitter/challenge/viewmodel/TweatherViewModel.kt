package com.twitter.challenge.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.twitter.challenge.data.Result
import com.twitter.challenge.data.TweatherRepository
import com.twitter.challenge.data.Weather
import com.twitter.challenge.util.calculateStandardDeviation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TweatherViewModel internal constructor(
    private val repository: TweatherRepository
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        Log.e("TweatherViewModel", e.message, e)
    }

    private val currentWeather = MutableLiveData<Weather>()
    val currentWeatherLiveData: LiveData<Weather> = currentWeather

    private val futureWeather = MutableLiveData<List<Weather>>()
    val standardDeviationLiveData: LiveData<Double> = Transformations.map(
        futureWeather
    ) { list -> calculateStandardDeviation(list.map { it.temperature ?: 0.0 }) }

    fun updateCurrentWeather() {
        viewModelScope.launch(exceptionHandler) {
            when (val result = repository.getCurrentWeather()) {
                is Result.Success -> {
                    Log.d("TweatherViewModel", "Current weather: ${result.data}")
                    currentWeather.value = result.data
                }
                is Result.Error -> {
                    Log.e("TweatherViewModel", result.exception.message, result.exception)
                }
            }
        }
    }

    fun updateStandardDeviation() = updateFutureWeather()

    private fun updateFutureWeather(days: Int = 5) {
        viewModelScope.launch(exceptionHandler) {
            when (val result = repository.getFutureWeather(days)) {
                is Result.Success -> {
                    Log.d("TweatherViewModel", "Future weather ($days days): ${result.data}")
                    futureWeather.value = result.data
                }
                is Result.Error -> {
                    Log.e("TweatherViewModel", result.exception.message, result.exception)
                }
            }
        }
    }
}