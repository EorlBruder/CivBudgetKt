package de.eorlbruder.civbudget.server.rates.service

import de.eorlbruder.civbudget.server.rates.dto.RateDTO

interface RateService {
    fun findAll(): Iterable<RateDTO>

    fun find(id: Long): RateDTO?

    fun save(rateDto: RateDTO)

    fun delete(id: Long)

    fun recalculate()
}