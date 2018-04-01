package de.eorlbruder.civbudget.server.magicnumber.service

import de.eorlbruder.civbudget.server.magicnumber.dto.MagicNumberDTO

interface MagicNumberService {

    fun findCurrent(): MagicNumberDTO?

    fun findAll(): Iterable<MagicNumberDTO>

    fun recalculate()
}