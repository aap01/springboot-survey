package com.aap.springboot_skeleton.controller

import com.aap.springboot_skeleton.model.response.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingController {
    companion object {
        const val PING = "/ping"
    }
    @GetMapping(PING)
    fun send(): ResponseEntity<GenericResponse> {
        return ResponseEntity.ok(GenericResponse(message = "Server is working!"))
    }
}