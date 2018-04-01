package de.eorlbruder.civbudget.server.magicnumber.service

import de.eorlbruder.civbudget.server.magicnumber.dto.MagicNumberDTO
import de.eorlbruder.civbudget.server.magicnumber.dto.MagicNumberMapper
import de.eorlbruder.civbudget.server.magicnumber.repository.MagicNumberRepository
import org.springframework.stereotype.Service

@Service
class MagicNumberServiceImpl(private val repository: MagicNumberRepository) : MagicNumberService {

    override fun findCurrent(): MagicNumberDTO? {
        val currentMagicNumber = repository.findFirstByOrderByCalculatedDate() ?: return null
        return MagicNumberMapper().map(currentMagicNumber)
    }

    override fun findAll() = MagicNumberMapper().mapToDTOs(repository.findAll())

    override fun recalculate() {
        // TODO eorlbruder 01.04.2018 - here we need to think about how we want to recalculate? We do actually want to
        // be able to trigger this from the clients - because changes in many areas will be affecting the magic Number
        // but at the same time we will want to periodically recalculate the magic number from a cronjob (or something
        // similiar). This seems to be another "batch"-package (and program) just for that. Should this function just
        // trigger the batchjob? Else we'd have duplicate code in the most crucial part of the program - so it will
        // be that in the end. Maybe we could do some batchjob-scheduling but that could potentially blow things up
    }

}