package com.ca.network.error

sealed class NetworkError : Throwable() {
    object ServerError : NetworkError()
    data class AuthenticationError(override val message: String) : NetworkError()
}