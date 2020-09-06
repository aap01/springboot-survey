package com.aap.springboot_skeleton.filter

import com.aap.springboot_skeleton.exception.GenericError
import com.aap.springboot_skeleton.util.Constants.Companion.AUTHORIZATION
import com.aap.springboot_skeleton.service.JwtService
import com.aap.springboot_skeleton.service.MyUserDetailsService
import com.aap.springboot_skeleton.util.Resources
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userDetailsService: MyUserDetailsService

    @Autowired
    private lateinit var jwtService: JwtService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = request.getHeader(AUTHORIZATION)
        var username: String? = null
        try {
            if (!token.isNullOrEmpty())
                username = jwtService.extractUsername(token)
            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(username)

                if (jwtService.isTokenValid(token, userDetails)) {
                    val authToken =
                        UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.password, userDetails.authorities
                        )
                    authToken.details = WebAuthenticationDetailsSource()
                        .buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
            filterChain.doFilter(request, response)

        } catch (e: JwtException) {
            response.status = HttpStatus.BAD_REQUEST.value()
            response.contentType = "application/json"
            val error = GenericError(message = Resources.invalidToken, httpStatus = HttpStatus.UNAUTHORIZED)
            response.writer.write(error.toJsonString())
        }

    }
}