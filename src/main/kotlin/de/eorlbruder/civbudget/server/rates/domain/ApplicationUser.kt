package de.eorlbruder.civbudget.server.rates.domain

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ApplicationUser(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @get: NotEmpty
        var username: String = "",

        @get: NotEmpty
        var password: String = ""
)
