package de.eorlbruder.civbudget.server.rates.calculator

import de.eorlbruder.civbudget.server.rates.domain.Rate
import java.time.LocalDate

class MagicNumberCalculator(val rates: Iterable<Rate>, val now: LocalDate = LocalDate.now()) {

    fun calculate(): Double {
        var magicNumberValue = 0.0
        for (rate in rates) {
            rate.dailyValue = RateCalculator(rate, now).calculate()
            magicNumberValue += rate.dailyValue
        }
        return magicNumberValue
    }
}