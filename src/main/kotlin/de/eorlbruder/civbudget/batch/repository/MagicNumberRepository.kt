package de.eorlbruder.civbudget.batch.repository

import de.eorlbruder.civbudget.batch.domain.MagicNumber
import org.springframework.data.repository.CrudRepository

interface MagicNumberRepository : CrudRepository<MagicNumber, Long> {
    fun findFirstByOrderByCalculatedDate(): MagicNumber?
}