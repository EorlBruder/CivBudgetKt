package de.eorlbruder.civbudget.server.magicnumber.domain

import org.hibernate.validator.constraints.NotBlank
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class MagicNumber(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @get: NotBlank
        var value: Double = 0.0,

        @get: NotBlank
        var rateValue: Double = 0.0,

        @get: NotBlank
        var calculatedDate: LocalDate = LocalDate.now()
)