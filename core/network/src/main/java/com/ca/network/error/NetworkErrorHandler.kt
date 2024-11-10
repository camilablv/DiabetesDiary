package com.ca.network.error

import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.exception.ApolloHttpException

class NetworkErrorHandler {

    suspend fun <T: Operation.Data>withErrorHandler(request: suspend () -> ApolloResponse<T>): Result<T> {
        try {
            val response = request.invoke()
            if (response.errors != null) {
                val error = parse(response.errors!!.first())
                return Result.failure(error)
            }
            return Result.success(response.data!!)
        } catch (exception: ApolloHttpException) {
            return Result.failure(NetworkError.ServerError)
        }
    }

    private fun parse(error: Error): NetworkError {
        return when (error.nonStandardFields?.get("code")?.toString()?.toInt()) {
            401 -> { NetworkError.AuthenticationError(error.message) }
            else -> { NetworkError.ServerError }
        }
    }
}