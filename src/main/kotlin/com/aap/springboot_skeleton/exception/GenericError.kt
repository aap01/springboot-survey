package com.aap.springboot_skeleton.exception

import org.springframework.http.HttpStatus

data class GenericError(
    val message: String? = "Internal server error",
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val enum: String = ErrorEnum.NOT_SET.toString()
) {
    fun toJsonString() =
        "{\n"+
        "\"message\"" + ":" + "\"$message\"" + ",\n" +
        "\"httpStatus\"" + ":" + "\"${httpStatus.name}\"" +",\n" +
        "\"enum\"" + ":" + "\"$enum\"" +
        "\n}"
}