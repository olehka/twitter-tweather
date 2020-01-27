package com.twitter.challenge.util

import com.twitter.challenge.api.TweatherClient
import com.twitter.challenge.api.TweatherService
import com.twitter.challenge.data.TweatherRepository
import com.twitter.challenge.viewmodel.TweatherViewModelFactory

object InjectorUtils {

    fun provideTweatherViewModelFactory() =
        TweatherViewModelFactory(getTweatherRepository())

    private fun getTweatherRepository(): TweatherRepository =
        TweatherRepository.getInstance(getTweatherService())

    private fun getTweatherService(): TweatherService =
        TweatherClient.tweatherService
}