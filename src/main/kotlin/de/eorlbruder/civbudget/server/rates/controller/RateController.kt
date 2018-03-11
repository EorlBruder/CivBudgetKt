package de.eorlbruder.civbudget.server.rates.controller

import de.eorlbruder.civbudget.server.rates.calculator.RateCalculator
import de.eorlbruder.civbudget.server.rates.dto.RateDTO
import de.eorlbruder.civbudget.server.rates.dto.RateMapper
import de.eorlbruder.civbudget.server.rates.repository.RateRepository
import org.springframework.web.bind.annotation.*

@RestController
class RateController(private val repository: RateRepository) {

    @GetMapping("/rates")
    fun findAll()
            = RateMapper().mapToDtos(repository.findAllByOrderByDailyValue())

    @GetMapping("/rates/id/{id}")
    fun findById(@PathVariable id: Long)
            = RateMapper().map(repository.findOne(id))

    @PostMapping("/rates/update")
    fun update(@RequestBody rateDto: RateDTO) {
        val rate = RateMapper().map(rateDto)
        val rateCalculator = RateCalculator(rate)
        rate.dailyValue = rateCalculator.calculate()
        repository.save(rate)
    }

    @PostMapping("/rates/delete")
    fun delete(@RequestBody rateDto: RateDTO) {
        val rate = RateMapper().map(rateDto)
        repository.delete(rate)
    }


    @PostMapping("/rates/recalculate")
    fun recalculate() {
        val rates = repository.findAll()
        for (rate in rates) {
            val rateCalculator = RateCalculator(rate)
            val newValue = rateCalculator.calculate()
            if (newValue != rate.dailyValue) {
                rate.dailyValue = newValue
            }
        }
        repository.save(rates)
    }
}