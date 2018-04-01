package de.eorlbruder.civbudget.server.rates.dto

import de.eorlbruder.civbudget.server.rates.domain.Cycle
import de.eorlbruder.civbudget.server.rates.domain.Rate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RateMapper {

    fun map(dto: RateDTO): Rate {
        return Rate(dto.id, dto.name, dto.value, Cycle.valueOf(dto.cycle),
                LocalDate.parse(dto.startDate, DateTimeFormatter.ISO_LOCAL_DATE), dto.dailyValue)
    }

    fun map(domain: Rate): RateDTO {
        return RateDTO(domain.id, domain.name, domain.value, domain.cycle.toString(),
                domain.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE), domain.dailyValue)
    }

    fun mapToDomains(dtos: Iterable<RateDTO>): Iterable<Rate> {
        val result = ArrayList<Rate>()
        dtos.mapTo(result) { map(it) }
        return result
    }

    fun mapToDTOs(domains: Iterable<Rate>): Iterable<RateDTO> {
        val result = ArrayList<RateDTO>()
        domains.mapTo(result) { map(it) }
        return result
    }
}