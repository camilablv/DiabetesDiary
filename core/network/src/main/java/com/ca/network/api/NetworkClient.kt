package com.ca.network.api

import com.apollographql.apollo3.ApolloClient
import com.ca.CreateSessionByGoogleIdTokenMutation
import com.ca.network.error.NetworkErrorHandler
import javax.inject.Inject

class NetworkClient @Inject constructor(
    private val apolloClient: ApolloClient,
    private val errorHandler: NetworkErrorHandler
) {

    suspend fun createSession(idToken: String): Result<CreateSessionByGoogleIdTokenMutation.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient
                .mutation(CreateSessionByGoogleIdTokenMutation(idToken))
                .execute()
        }
    }
}