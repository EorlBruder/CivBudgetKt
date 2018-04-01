package de.eorlbruder.civbudget.batch.magicnumber

import de.eorlbruder.civbudget.batch.magicnumber.calculator.MagicNumberCalculator
import de.eorlbruder.civbudget.batch.repository.MagicNumberRepository
import de.eorlbruder.civbudget.batch.repository.RateRepository

class MagicNumberProcessor(private val magicNumberRepository: MagicNumberRepository, private val rateRepository: RateRepository) {

    fun process() {
        val updatedMagicNumber = MagicNumberCalculator(rateRepository.findAll()).calculate()
        val currentMagicNumber = magicNumberRepository.findFirstByOrderByCalculatedDate()
        if (currentMagicNumber == null || currentMagicNumber.value != updatedMagicNumber.value) {
            magicNumberRepository.save(updatedMagicNumber)
        }
    }

}