package com.aap.springboot_skeleton.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceExistsException::class)
    fun handleConflict(e: ResourceExistsException): ResponseEntity<GenericError> {
        val error = GenericError(
            message = e.message,
            httpStatus = HttpStatus.CONFLICT
        )
        return ResponseEntity(error, error.httpStatus)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException): ResponseEntity<GenericError> {
        val error = GenericError(
            message = e.message,
            httpStatus = HttpStatus.NOT_FOUND
        )
        return ResponseEntity(error, error.httpStatus)
    }

    @ExceptionHandler(UnAuthorizedException::class)
    fun handleUnAuthorized(e: UnAuthorizedException): ResponseEntity<GenericError> {
        val error = GenericError(
            message = e.message,
            httpStatus = HttpStatus.UNAUTHORIZED
        )
        return ResponseEntity(error, error.httpStatus)
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(e: Exception): ResponseEntity<GenericError> {
        val error = GenericError(
            message = e.message,
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        )
        return ResponseEntity(error, error.httpStatus)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val it = ex.bindingResult.fieldErrors[0]
        var message = (it.field + " " + it.defaultMessage)
        if (message.isEmpty()) {
            val it = ex.bindingResult.globalErrors[0]
            message = (it.objectName + " " + it.defaultMessage)
        }
        val genericError = GenericError(message = message, httpStatus = status)
        return ResponseEntity(genericError, genericError.httpStatus)
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val message = ex.parameterName + " " + ex.localizedMessage
        val genericError = GenericError(message = message, httpStatus = status)
        return ResponseEntity(genericError, genericError.httpStatus)
    }
}