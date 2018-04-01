package de.eorlbruder.civbudget.server.magicnumber.controller

import de.eorlbruder.civbudget.server.magicnumber.service.MagicNumberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MagicNumberController(private val service: MagicNumberService) {

    @GetMapping("/magic_number")
    fun findCurrent(): Double {
        // TODO dm 01.04.2018 - or do we want the full object here?
        val currentMagicNumber = service.findCurrent() ?: return 0.0
        return currentMagicNumber.value

    }

    @GetMapping("/magic_number/all")
    fun findAll() = service.findAll()

    @PostMapping("/magic_number/recalculate")
    fun recalculate() {
        service.recalculate()
        // Todo dm 01.04.2018 - do we return something here?
    }
}


