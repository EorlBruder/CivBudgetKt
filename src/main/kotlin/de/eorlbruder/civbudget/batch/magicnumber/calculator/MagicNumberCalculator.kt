package de.eorlbruder.civbudget.batch.magicnumber.calculator

import de.eorlbruder.civbudget.batch.domain.Rate

class MagicNumberCalculator(val rates: Iterable<Rate>) {

    fun calculate(): Double {

        var magicNumberValue = 0.0
        rates.forEach {
            magicNumberValue += it.dailyValue
        }
        return magicNumberValue
    }
}