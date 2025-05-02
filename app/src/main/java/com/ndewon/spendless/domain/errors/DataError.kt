package com.ndewon.spendless.domain.errors

interface DataError : Error {
//    enum class NetworkError : DataError {
//        REQUEST_TIMEOUT,
//        TOO_MANY_REQUESTS,
//        NO_INTERNET,
//        PAYLOAD_TOO_LARGE,
//        SERVER_ERROR,
//        SERIALIZATION,
//    }

    enum class LocalError : DataError {
        USER_ALREADY_EXISTS,
        USER_NOT_FOUND,
        PREFERENCE_NOT_FOUND,
        UNKNOWN,
    }
}