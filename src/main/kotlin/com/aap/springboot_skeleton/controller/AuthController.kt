package com.aap.springboot_skeleton.controller

import com.aap.springboot_skeleton.model.request.AuthRequest
import com.aap.springboot_skeleton.model.response.AuthResponse
import com.aap.springboot_skeleton.model.response.GenericResponse
import com.aap.springboot_skeleton.model.response.SuccessResponse
import com.aap.springboot_skeleton.service.JwtService
import com.aap.springboot_skeleton.service.MyUserDetailsService
import com.aap.springboot_skeleton.util.Resources
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@CrossOrigin
class AuthController {

    companion object {
        const val LOGIN = "/login"
        const val SIGN_UP = "/signup"
    }

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userDetailsService: MyUserDetailsService

    @Autowired
    private lateinit var jwtService: JwtService


    @PostMapping(LOGIN)
    fun login(@Valid @RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password
            )
        )
        val userDetails = userDetailsService
            .loadUserByUsername(authRequest.username)
        val token = jwtService.generateToken(userDetails)
        return ResponseEntity.ok(
            SuccessResponse(
                message = "${Resources.welcomeBack} ${authRequest.username}!",
                body = AuthResponse(token)
            )
        )
    }

    @PostMapping(SIGN_UP)
    fun signUp(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        val user = userDetailsService.signUpUser(authRequest)
        return ResponseEntity.ok(GenericResponse("${Resources.welcome}, ${user.username}!"))
    }
}