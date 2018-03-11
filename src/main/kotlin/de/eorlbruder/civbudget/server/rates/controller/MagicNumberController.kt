package de.eorlbruder.civbudget.server.rates.controller

import de.eorlbruder.civbudget.server.rates.calculator.MagicNumberCalculator
import de.eorlbruder.civbudget.server.rates.domain.MagicNumber
import de.eorlbruder.civbudget.server.rates.repository.MagicNumberRepository
import de.eorlbruder.civbudget.server.rates.repository.RateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class MagicNumberController(private val repository: MagicNumberRepository, private val rateRepository: RateRepository) {

    @GetMapping("/magic_number")
    fun findLatest()
            = repository.findFirstByOrderByCalculatedDate()

    @GetMapping("/magic_number/all")
    fun findAll(): Iterable<MagicNumber>
            = repository.findAll()!!

    @PostMapping("/magic_number/recalculate")
    fun recalculate() {
        val newValue = MagicNumberCalculator(rateRepository.findAll()).calculate()
        val magicNumber = repository.findFirstByOrderByCalculatedDate()
        if (magicNumber == null || newValue != magicNumber.value) {
            val newMagicNumber = MagicNumber(value = newValue, calculatedDate = LocalDate.now())
            repository.save(newMagicNumber)
        }
    }
}


