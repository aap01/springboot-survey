package com.aap.springboot_skeleton.model.request


import com.aap.springboot_skeleton.util.Resources
import javax.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank(message = Resources.required)
    val username: String,
    @field:NotBlank(message = Resources.required)
    val password: String
)