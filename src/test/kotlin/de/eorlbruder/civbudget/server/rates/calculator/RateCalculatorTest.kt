package de.eorlbruder.civbudget.server.rates.calculator

import de.eorlbruder.civbudget.server.rates.domain.Cycle
import de.eorlbruder.civbudget.server.rates.domain.Rate
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

internal class RateCalculatorTest {

    private val DELTA = 0.01


    @Test
    fun dailyValueWithShouldBeZeroBeforeStartDate() {
        val expected = 0.0

        val rate = Rate(value = 2.0, cycle = Cycle.DAILY, startDate = LocalDate.of(2018, 2, 1))
        val sut = RateCalculator(rate, LocalDate.of(2018, 1, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)

    }

    @Test
    fun dailyValueShouldEqualValue() {
        val expected = 2.0

        val rate = Rate(value = 2.0, cycle = Cycle.DAILY)
        val sut = RateCalculator(rate)

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun weeklyValueShouldValueDividedBySeven() {
        val expected = 2.0

        val rate = Rate(value = 14.0, cycle = Cycle.WEEKLY)
        val sut = RateCalculator(rate)

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun monthlyValueInThirtyDayMonthShouldBeDividedByThirty() {
        val expected = 2.0

        val rate = Rate(value = 60.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 4, 1))
        val sut = RateCalculator(rate, LocalDate.of(2018, 4, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun monthlyValueInThirtyOneDayMonthShouldBeDividedByThirtyOne() {
        val expected = 2.0

        val rate = Rate(value = 62.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 4, 1))
        val sut = RateCalculator(rate, LocalDate.of(2018, 5, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun monthlyValueInNormalFebruaryMonthShouldBeDividedByTwentyEight() {
        val expected = 2.0

        val rate = Rate(value = 56.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 2, 1))
        val sut = RateCalculator(rate, LocalDate.of(2018, 2, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun monthlyValueInLeapFebruaryMonthShouldBeDividedByTwentyNine() {
        val expected = 2.0

        val rate = Rate(value = 58.0, cycle = Cycle.MONTHLY, startDate = LocalDate.of(2017, 2, 1))
        val sut = RateCalculator(rate, LocalDate.of(2020, 2, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }

    @Test
    fun yearlyValueInNormalYearShouldBeDividedByThreeHundredSixtyFive() {
        val expected = 2.0

        val rate = Rate(value = 730.0, cycle = Cycle.YEARLY, startDate = LocalDate.of(2017, 1, 1))
        val sut = RateCalculator(rate, LocalDate.of(2018, 1, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }


    @Test
    fun yearlyValueInLeapYearShouldBeDividedByThreeHundredSixtySix() {
        val expected = 2.0

        val rate = Rate(value = 732.0, cycle = Cycle.YEARLY, startDate = LocalDate.of(2017, 1, 1))
        val sut = RateCalculator(rate, LocalDate.of(2020, 1, 1))

        val actual = sut.calculate()

        Assert.assertEquals(expected, actual, DELTA)
    }
}