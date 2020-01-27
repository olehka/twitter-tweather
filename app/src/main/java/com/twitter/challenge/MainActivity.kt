package com.twitter.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.twitter.challenge.data.Weather
import com.twitter.challenge.databinding.ActivityMainBinding
import com.twitter.challenge.util.InjectorUtils
import com.twitter.challenge.util.celsiusToFahrenheit
import com.twitter.challenge.viewmodel.TweatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TweatherViewModel by viewModels {
        InjectorUtils.provideTweatherViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        subscribeUi()
        updateCurrentWeather()
    }

    private fun subscribeUi() {
        subscribeCurrentWeather()
        subscribeStandardDeviation()
    }

    private fun subscribeCurrentWeather() {
        binding.tempTextView.text = getString(R.string.temperature_format, 0.0, celsiusToFahrenheit(0.0))
        val currentWeatherObserver = Observer<Weather> { weather ->
            Log.d("MainActivity", "Current weather: $weather")
            binding.tempTextView.text = getString(R.string.temperature_format, weather.temperature, celsiusToFahrenheit(weather.temperature ?: 0.0))
            binding.windTextView.text = weather.windSpeed.toString()
            binding.cloudImageView.visibility =
                if (weather.cloudiness != null && weather.cloudiness > 50) View.VISIBLE
                else View.GONE
        }
        viewModel.currentWeatherLiveData.observe(this, currentWeatherObserver)
    }

    private fun subscribeStandardDeviation() {
        binding.sdevTextView.text = getString(R.string.standard_deviation_format, 0.0)
        binding.sdevButton.setOnClickListener { onStandardDeviationButtonClick() }
        val standardDeviationObserver = Observer<Double> { stddev ->
            Log.d("MainActivity", "Standard Deviation: $stddev")
            binding.sdevTextView.text = getString(R.string.standard_deviation_format, stddev)
        }
        viewModel.standardDeviationLiveData.observe(this, standardDeviationObserver)
    }

    private fun updateCurrentWeather() = viewModel.updateCurrentWeather()

    private fun onStandardDeviationButtonClick() = viewModel.updateStandardDeviation()
}
