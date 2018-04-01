package de.eorlbruder.civbudget.server.magicnumber.dto

import de.eorlbruder.civbudget.server.magicnumber.domain.MagicNumber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO eorlbruder 01.04.2018 - isn't the Mapper just inherhiting some kind of AbstractMapper<DOMAIN, DTO> or implementing that interface?
// At least the maptToDomains and MapToDTOs look always the same and everything else is just boilerplate...
class MagicNumberMapper {

    fun map(dto: MagicNumberDTO): MagicNumber {
        return MagicNumber(dto.id, dto.value, dto.rateValue, LocalDate.parse(dto.calculatedDate, DateTimeFormatter.ISO_LOCAL_DATE))
    }

    fun map(domain: MagicNumber): MagicNumberDTO {
        return MagicNumberDTO(domain.id, domain.value, domain.rateValue, domain.calculatedDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    fun mapToDomains(dtos: Iterable<MagicNumberDTO>): Iterable<MagicNumber> {
        val result = ArrayList<MagicNumber>()
        dtos.mapTo(result) { map(it) }
        return result
    }

    fun mapToDTOs(domains: Iterable<MagicNumber>): Iterable<MagicNumberDTO> {
        val result = ArrayList<MagicNumberDTO>()
        domains.mapTo(result) { map(it) }
        return result
    }
}