package com.twitter.challenge.util

import kotlin.math.pow
import kotlin.math.sqrt

fun calculateStandardDeviation(temperatures: List<Double>): Double {
    val size = temperatures.size
    if (size < 2) {
        return 0.0
    }
    var sum = 0.0
    for (t in temperatures) {
        sum += t
    }
    val mean = sum / size
    sum = 0.0
    for (t in temperatures) {
        sum += (t - mean).pow(2.0)
    }
    return sqrt(sum / (size - 1))
}

fun celsiusToFahrenheit(temperatureInCelsius: Double): Double =
    temperatureInCelsius * 1.8 + 32