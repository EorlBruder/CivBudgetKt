package de.eorlbruder.civbudget.server.magicnumber.repository

import de.eorlbruder.civbudget.server.magicnumber.domain.MagicNumber
import org.springframework.data.repository.CrudRepository

interface MagicNumberRepository : CrudRepository<MagicNumber, Long> {
    fun findFirstByOrderByCalculatedDate(): MagicNumber?
}