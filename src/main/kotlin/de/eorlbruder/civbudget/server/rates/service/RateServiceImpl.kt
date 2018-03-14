package de.eorlbruder.civbudget.server.rates.service

import de.eorlbruder.civbudget.server.rates.calculator.RateCalculator
import de.eorlbruder.civbudget.server.rates.domain.Rate
import de.eorlbruder.civbudget.server.rates.dto.RateDTO
import de.eorlbruder.civbudget.server.rates.dto.RateMapper
import de.eorlbruder.civbudget.server.rates.repository.RateRepository
import org.springframework.stereotype.Service

@Service
class RateServiceImpl(private val repository: RateRepository) : RateService {

    override fun findAll(): Iterable<RateDTO> = RateMapper().mapToDtos(repository.findAllByOrderByDailyValue())

    override fun find(id: Long): RateDTO? {
        val rate = repository.findOne(id) ?: return null
        return RateMapper().map(rate)
    }

    override fun save(rateDto: RateDTO) {
        val rate = RateMapper().map(rateDto)
        val rateCalculator = RateCalculator(rate)
        rate.dailyValue = rateCalculator.calculate()
        repository.save(rate)
    }


    override fun delete(id: Long) {
        repository.delete(id)
    }


    override fun recalculate() {
        val rates = repository.findAll()
        rates.forEach(::recalcRate)
        repository.save(rates)
    }

    private fun recalcRate(rate: Rate) {
        val rateCalculator = RateCalculator(rate)
        val newValue = rateCalculator.calculate()
        if (newValue != rate.dailyValue) {
            rate.dailyValue = newValue
        }
    }

}