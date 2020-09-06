package com.aap.springboot_skeleton.controller

import com.aap.springboot_skeleton.model.response.GenericResponse
import com.aap.springboot_skeleton.model.response.HelloResponse
import com.aap.springboot_skeleton.model.response.SuccessResponse
import com.aap.springboot_skeleton.service.JwtService
import com.aap.springboot_skeleton.service.MyUserDetailsService
import com.aap.springboot_skeleton.util.Resources
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HelloController {

    @Autowired
    private lateinit var userDetailsService: MyUserDetailsService

    companion object {
        const val HELLO = "/hello"
    }

    @RequestMapping(HELLO)
    fun sayHello(
        principal: Principal
    ): ResponseEntity<GenericResponse> {
        val user = userDetailsService.getUser(principal.name)
        return ResponseEntity.ok(
            SuccessResponse(
                message = "${Resources.hello}, ${user.username}!",
                body = user
            )
        )
    }
}