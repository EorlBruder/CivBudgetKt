package de.eorlbruder.civbudget.server.security.service

import de.eorlbruder.civbudget.server.security.domain.ApplicationUser
import de.eorlbruder.civbudget.server.security.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {

    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return User(user.username, user.password, emptyList())
    }

    fun save(user: ApplicationUser) {
        userRepository.save(user)
    }
}