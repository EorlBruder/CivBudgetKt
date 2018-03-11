package de.eorlbruder.civbudget.server.rates.security

import de.eorlbruder.civbudget.server.rates.config.SIGN_UP_URL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.Filter


@Configuration
@EnableWebSecurity
class WebSecurity(val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    fun corsFilter(): Filter {
        val source = UrlBasedCorsConfigurationSource()
        val config: CorsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(corsFilter(), ChannelProcessingFilter::class.java)
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }
}