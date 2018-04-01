package de.eorlbruder.civbudget.batch

import de.eorlbruder.civbudget.batch.magicnumber.MagicNumberProcessor
import de.eorlbruder.civbudget.batch.rates.RateProcessor
import de.eorlbruder.civbudget.batch.repository.MagicNumberRepository
import de.eorlbruder.civbudget.batch.repository.RateRepository

// TODO eorlbruder - 01.04.2018 - Spring-foo and autowiring...
class Daily(private val rateRepository: RateRepository, private val magicNumberRepository: MagicNumberRepository) {

    fun run() {
        RateProcessor(rateRepository).process()
        MagicNumberProcessor(magicNumberRepository, rateRepository).process()
    }
}