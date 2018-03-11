package de.eorlbruder.civbudget.server.rates.domain

import org.hibernate.validator.constraints.NotBlank
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Rate(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @get: NotBlank
        var name: String = "",

        @get: NotNull
        var value: Double = 0.0,

        @get: NotNull
        var cycle: Cycle = Cycle.YEARLY,

        @get: NotNull
        var startDate: LocalDate = LocalDate.now(),

        @get: NotNull
        var dailyValue: Double = 0.0
)
