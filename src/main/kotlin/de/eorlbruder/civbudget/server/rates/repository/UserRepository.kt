package de.eorlbruder.civbudget.server.rates.repository

import de.eorlbruder.civbudget.server.security.domain.ApplicationUser
import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<ApplicationUser, Long> {
    fun findByUsername(username: String): ApplicationUser?
}