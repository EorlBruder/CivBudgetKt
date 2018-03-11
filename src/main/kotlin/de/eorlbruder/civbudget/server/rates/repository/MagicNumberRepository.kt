package de.eorlbruder.civbudget.server.rates.repository

import de.eorlbruder.civbudget.server.rates.domain.MagicNumber
import org.springframework.data.repository.CrudRepository

interface MagicNumberRepository : CrudRepository<MagicNumber, Long> {
    fun findFirstByOrderByCalculatedDate(): MagicNumber?
}