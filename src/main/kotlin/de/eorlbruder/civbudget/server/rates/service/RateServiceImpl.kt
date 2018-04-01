package de.eorlbruder.civbudget.server.rates.service

import de.eorlbruder.civbudget.server.rates.domain.Rate
import de.eorlbruder.civbudget.server.rates.dto.RateDTO
import de.eorlbruder.civbudget.server.rates.dto.RateMapper
import de.eorlbruder.civbudget.server.rates.repository.RateRepository
import org.springframework.stereotype.Service

@Service
class RateServiceImpl(private val repository: RateRepository) : RateService {

    override fun findAll(): Iterable<RateDTO> = RateMapper().mapToDTOs(repository.findAllByOrderByDailyValue())

    override fun find(id: Long): RateDTO? {
        val rate = repository.findOne(id) ?: return null
        return RateMapper().map(rate)
    }

    override fun save(rateDto: RateDTO) {
        val rate = RateMapper().map(rateDto)
        // TODO eorlburder 01.04.2018 - call batchjob here!
        rate.dailyValue = 0.0
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
        // TODO eorlburder 01.04.2018 - call batchjob here!
        val newValue = 0.0
        if (newValue != rate.dailyValue) {
            rate.dailyValue = newValue
        }
    }

}