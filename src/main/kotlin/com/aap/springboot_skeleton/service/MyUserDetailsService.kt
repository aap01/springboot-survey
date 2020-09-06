package com.aap.springboot_skeleton.service

import com.aap.springboot_skeleton.exception.NotFoundException
import com.aap.springboot_skeleton.model.request.AuthRequest
import com.aap.springboot_skeleton.model.DBAuth
import com.aap.springboot_skeleton.repository.AuthRepository
import com.aap.springboot_skeleton.model.MyUserDetails
import com.aap.springboot_skeleton.model.response.HelloResponse
import com.aap.springboot_skeleton.util.Resources
import com.aap.springboot_skeleton.util.throwException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MyUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var authRepository: AuthRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun loadUserByUsername(p0: String?): UserDetails {
        val e = NotFoundException(Resources.userNotFound)
        if (p0 == null) throw e
        else {
            val auth = authRepository.findByUsername(p0)
            auth.orElseThrow { e }
            return MyUserDetails(auth.get())
        }
    }

    @Transactional
    fun signUpUser(auth: AuthRequest): DBAuth {
//        if (auth.isValid()) {
            val dbAuth = DBAuth(
                username = auth.username.trim(),
                password = passwordEncoder.encode(auth.password.trim())
            )
            return authRepository.save(dbAuth)
//        }
//        else throw BadRequestException(auth.getInvalidMessage())
    }

    @Transactional
    fun getUser(name: String?): HelloResponse {
        val notFoundException = NotFoundException(Resources.userNotFound)
        return throwException({
            val optional = authRepository.findByUsername(name!!)
            optional.orElseThrow { notFoundException }
            val user = optional.get()
            HelloResponse(
                username = user.username,
                roles = user.roles.split(",")
            )
        }, notFoundException)
    }

}
