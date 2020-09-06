package com.aap.springboot_skeleton.exception

import java.lang.RuntimeException

data class ResourceExistsException(
    override val message: String
) : RuntimeException()