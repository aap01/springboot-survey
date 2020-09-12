package com.aap.springboot_skeleton.model.response

data class HelloResponse (
    val username: String,
    val roles: List<String>,
    val imageUrl: String? = null
)