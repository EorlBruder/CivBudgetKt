package de.eorlbruder.civbudget.server.rates.controller

import de.eorlbruder.civbudget.server.rates.dto.RateDTO
import de.eorlbruder.civbudget.server.rates.service.RateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RateController(private val service: RateService) {


    @GetMapping("/rates/list")
    fun findAll() = service.findAll()


    @GetMapping("/rates/show/{id}")
    fun find(@PathVariable id: Long) = service.find(id)


    @PostMapping("/rates/add")
    fun add(@RequestBody rateDto: RateDTO): ResponseEntity<String> {
        service.save(rateDto)
        return ResponseEntity("Rate saved successfully", HttpStatus.OK)
    }

    @PutMapping("/rates/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody rateDto: RateDTO): ResponseEntity<String> {
        if (service.find(id) == null) {
            return ResponseEntity("Rate to be updated not found", HttpStatus.NOT_FOUND)
        }
        service.save(rateDto)
        return ResponseEntity("Rate saved successfully", HttpStatus.OK)
    }

    @DeleteMapping("/rates/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity("Rate deleted successfully", HttpStatus.OK)
    }


    @PostMapping("/rates/recalculate")
    fun recalculate(): ResponseEntity<String> {
        service.recalculate()
        return ResponseEntity("Successfully recalculated rates", HttpStatus.OK)
    }
}