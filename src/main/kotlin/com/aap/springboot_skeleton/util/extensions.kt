package com.aap.springboot_skeleton.util

fun <T> throwException(fn: () -> T, e: Exception): T {
    var t: T? = null
    try {
        t = fn.invoke()
    } catch (anyException: Exception) {
        throw e
    }
    return t
}