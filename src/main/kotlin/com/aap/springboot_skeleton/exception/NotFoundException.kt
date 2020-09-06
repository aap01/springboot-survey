package com.aap.springboot_skeleton.exception

import java.lang.RuntimeException

data class NotFoundException(override val message: String) : RuntimeException()