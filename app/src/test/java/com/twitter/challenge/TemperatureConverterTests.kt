package com.twitter.challenge

import com.twitter.challenge.util.celsiusToFahrenheit
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureConverterTests {

    @Test
    fun testCelsiusToFahrenheitConversion() {
        val delta = 0.01
        assertEquals(celsiusToFahrenheit(-50.0), -58.0, delta)
        assertEquals(celsiusToFahrenheit(0.0), 32.0, delta)
        assertEquals(celsiusToFahrenheit(10.0), 50.0, delta)
        assertEquals(celsiusToFahrenheit(21.11), 70.0, delta)
        assertEquals(celsiusToFahrenheit(37.78), 100.0, delta)
        assertEquals(celsiusToFahrenheit(1000.0), 1832.0, delta)
        assertEquals(celsiusToFahrenheit(100.0), 212.0, delta)
        assertEquals(celsiusToFahrenheit(-100.0), -148.0, delta)
        assertEquals(celsiusToFahrenheit(-15.0), 5.0, delta)
        assertEquals(celsiusToFahrenheit(-1000.0), -1768.0, delta)
    }
}