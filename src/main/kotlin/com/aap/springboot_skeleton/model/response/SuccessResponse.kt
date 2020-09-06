package com.aap.springboot_skeleton.model.response

data class SuccessResponse<T>(
    override val message: String,
    val body: T
) : GenericResponse(message)