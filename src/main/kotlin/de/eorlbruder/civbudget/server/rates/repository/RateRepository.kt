package de.eorlbruder.civbudget.server.rates.repository

import de.eorlbruder.civbudget.server.rates.domain.Rate
import org.springframework.data.repository.CrudRepository

interface RateRepository : CrudRepository<Rate, Long> {
    fun findAllByOrderByDailyValue(): Iterable<Rate>
}