package com.ndewon.spendless.utils

import com.ndewon.spendless.domain.errors.DataError

fun DataError.toMessage(): String {
    return when (this) {
        DataError.LocalError.USER_ALREADY_EXISTS -> "This username has already been taken!"
        DataError.LocalError.USER_NOT_FOUND -> "User not found"
        DataError.LocalError.UNKNOWN -> "Something went wrong!"
        else -> {
            ""
        }
    }
}