package de.eorlbruder.civbudget.batch.rates

import de.eorlbruder.civbudget.batch.rates.calculator.RateCalculator
import de.eorlbruder.civbudget.batch.repository.RateRepository
import java.time.LocalDate

class RateProcessor(private val repository: RateRepository) {

    fun process() {
        val rates = repository.findAll()
        rates.forEach {
            val rateCalculator = RateCalculator(it, LocalDate.now())
            it.dailyValue = rateCalculator.calculate()
            repository.save(it)
        }
    }

}