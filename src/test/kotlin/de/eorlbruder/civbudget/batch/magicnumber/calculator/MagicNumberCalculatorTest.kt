package de.eorlbruder.civbudget.batch.magicnumber.calculator

import de.eorlbruder.civbudget.batch.domain.Rate
import org.junit.Assert
import org.junit.Test

internal class MagicNumberCalculatorTest {

    @Test
    fun testMagicNumberCalculate() {
        val expected = 5.0

        val rates = ArrayList<Rate>()
        rates.add(Rate(dailyValue = 2.0))
        rates.add(Rate(dailyValue = 3.0))
        val sut = MagicNumberCalculator(rates)

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, 0.01)
    }
}

