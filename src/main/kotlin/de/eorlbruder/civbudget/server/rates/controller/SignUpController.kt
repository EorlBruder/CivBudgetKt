package de.eorlbruder.civbudget.server.rates.controller

import de.eorlbruder.civbudget.server.rates.domain.ApplicationUser
import de.eorlbruder.civbudget.server.rates.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class SignUpController(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: ApplicationUser) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        userRepository.save(user)
    }
}