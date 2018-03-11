package de.eorlbruder.civbudget.server.rates.security

import de.eorlbruder.civbudget.server.rates.config.HEADER_STRING
import de.eorlbruder.civbudget.server.rates.config.SECRET
import de.eorlbruder.civbudget.server.rates.config.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpHeaders.ORIGIN
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.util.Collections.emptyList
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {

        if (request.getHeader(ORIGIN) != "null") {
            response.setHeader("Access-Control-Allow-Origin", "*")
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
            response.setHeader("Access-Control-Max-Age", "3600")
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization")

        }
        if (request.method == "OPTIONS") {
            response.status = HttpServletResponse.SC_OK
            return
        }

        val header = request.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        val authentication = getAuthentication(request)

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(HEADER_STRING)
        if (token != null) {
            // parse the token.
            val user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body
                    .subject

            if (user != null)
                return UsernamePasswordAuthenticationToken(user, null, emptyList<GrantedAuthority>())
            else
                return null
        }
        return null
    }
}