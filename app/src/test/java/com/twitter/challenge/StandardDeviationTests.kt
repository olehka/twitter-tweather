package com.twitter.challenge

import com.twitter.challenge.util.calculateStandardDeviation
import org.junit.Assert.assertEquals
import org.junit.Test

class StandardDeviationTests {

    @Test
    fun testStandardDeviationCalculation_zeroElements() {
        val delta = 0.00000001
        val input = emptyList<Double>()
        assertEquals(calculateStandardDeviation(input), 0.0, delta)
    }

    @Test
    fun testStandardDeviationCalculation_singleElement() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(10.0)
        assertEquals(calculateStandardDeviation(input), 0.0, delta)
    }

    @Test
    fun testStandardDeviationCalculation_twoElements() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(10.0)
        input.add(20.0)
        assertEquals(calculateStandardDeviation(input), 7.07106781, delta)
    }

    @Test
    fun testStandardDeviationCalculation_negativeNumbers() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(-10.0)
        input.add(-20.0)
        assertEquals(calculateStandardDeviation(input), 7.07106781, delta)
    }

    @Test
    fun testStandardDeviationCalculation_bigPositiveNumbers() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(10_000_000.123456789)
        input.add(20_100_000.123456789)
        input.add(30_020_000.123456789)
        input.add(40_003_000.123456789)
        input.add(50_000_400.123456789)
        assertEquals(calculateStandardDeviation(input), 1.579622869269751E7, delta)
    }

    @Test
    fun testStandardDeviationCalculation_bigNegativeNumbers() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(-10_000_000.123456789)
        input.add(-20_100_000.123456789)
        input.add(-30_020_000.123456789)
        input.add(-40_003_000.123456789)
        input.add(-50_000_400.123456789)
        assertEquals(calculateStandardDeviation(input), 1.579622869269751E7, delta)
    }

    @Test
    fun testStandardDeviationCalculation_bigMixedNumbers() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(-10_000_000.123456789)
        input.add(-20_000_000.123456789)
        input.add(30_020_000.123456789)
        input.add(40_003_000.123456789)
        input.add(50_000_400.123456789)
        assertEquals(calculateStandardDeviation(input), 3.1147383245809007E7, delta)
    }

    @Test
    fun testStandardDeviationCalculation_smallNumbers() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(0.0000000001)
        input.add(0.0000000002)
        input.add(-0.0000000003)
        input.add(-0.0000000004)
        assertEquals(calculateStandardDeviation(input), 0.0, delta)
    }

    @Test
    fun testStandardDeviationCalculation_allEquals() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(88.888888)
        input.add(88.888888)
        input.add(88.888888)
        input.add(88.888888)
        input.add(88.888888)
        assertEquals(calculateStandardDeviation(input), 0.0, delta)
    }

    @Test
    fun testStandardDeviationCalculation_scientificNotation() {
        val delta = 0.00000001
        val input = mutableListOf<Double>()
        input.add(1.123456789E10)
        input.add(2.123456789E11)
        input.add(3.123456789E12)
        input.add(4.123456789E13)
        input.add(5.123456789E14)
        assertEquals(calculateStandardDeviation(input), 2.2481914152373578E14, delta)
    }
}