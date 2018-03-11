package de.eorlbruder.civbudget.server.rates.calculator

import de.eorlbruder.civbudget.server.rates.domain.Cycle
import de.eorlbruder.civbudget.server.rates.domain.Rate
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

internal class MagicNumberCalculatorTest {

    @Test
    fun testMagicNumberCalculate() {
        val expected = 3.0

        val rates = ArrayList<Rate>()
        rates.add(Rate(value = 60.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 4, 1)))
        rates.add(Rate(value = 30.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 4, 1)))
        val sut = MagicNumberCalculator(rates, LocalDate.of(2018, 4, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, 0.01)
    }
}

