package com.twitter.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.twitter.challenge.data.Weather
import com.twitter.challenge.util.InjectorUtils
import com.twitter.challenge.viewmodel.TweatherViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: TweatherViewModel by viewModels {
        InjectorUtils.provideTweatherViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeUi()
        updateCurrentWeather()
    }

    private fun subscribeUi() {
        val currentWeatherObserver = Observer<Weather> { weather ->
            Log.d("MainActivity", "Current weather: $weather")
        }
        viewModel.currentWeatherLiveData.observe(this, currentWeatherObserver)
        val standardDeviationObserver = Observer<Double> { stddev ->
            Log.d("MainActivity", "Standard Deviation: $stddev")
        }
        viewModel.standardDeviationLiveData.observe(this, standardDeviationObserver)
    }

    private fun updateCurrentWeather() = viewModel.updateCurrentWeather()

    private fun onStandartDeviationButtonClick() = viewModel.updateStandardDeviation()
}
