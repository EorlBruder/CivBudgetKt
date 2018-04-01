package de.eorlbruder.civbudget.batch.magicnumber.calculator

import de.eorlbruder.civbudget.batch.domain.MagicNumber
import de.eorlbruder.civbudget.batch.domain.Rate
import java.time.LocalDate

class MagicNumberCalculator(val rates: Iterable<Rate>) {

    fun calculate(): MagicNumber {
        val rateValue = calculateRateValue()
        return MagicNumber(value = rateValue, rateValue = rateValue, calculatedDate = LocalDate.now())
    }

    private fun calculateRateValue(): Double {
        var rateValue = 0.0
        rates.forEach {
            rateValue += it.dailyValue
        }
        return rateValue
    }
}