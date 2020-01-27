package com.twitter.challenge.viewmodel

import androidx.lifecycle.*
import com.twitter.challenge.data.Result
import com.twitter.challenge.data.TweatherRepository
import com.twitter.challenge.data.Weather
import com.twitter.challenge.util.calculateStandardDeviation
import kotlinx.coroutines.launch

class TweatherViewModel internal constructor(
    private val repository: TweatherRepository) : ViewModel() {

    private val currentWeather = MutableLiveData<Weather>()
    val currentWeatherLiveData: LiveData<Weather> = currentWeather

    private val futureWeather = MutableLiveData<List<Weather>>()
    val standardDeviationLiveData: LiveData<Double> = Transformations.map(
        futureWeather
    ) { list -> calculateStandardDeviation(list.map { it.temperature ?: 0.0 }) }

    fun updateCurrentWeather() {
        viewModelScope.launch {
            val result = repository.getCurrentWeather()
            if (result is Result.Success) {
                currentWeather.value = result.data
            }
        }
    }

    fun updateStandardDeviation() = updateFutureWeather()

    private fun updateFutureWeather(days: Int = 5) {
        viewModelScope.launch {
            val result = repository.getFutureWeather(days)
            if (result is Result.Success) {
                futureWeather.value = result.data
            }
        }
    }
}