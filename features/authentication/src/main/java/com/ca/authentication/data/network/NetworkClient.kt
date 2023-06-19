package com.ca.authentication.data.network

import com.apollographql.apollo3.ApolloClient
import com.ca.CreateSessionByGoogleIdTokenMutation
import javax.inject.Inject

class NetworkClient @Inject constructor(
    private val apolloClient: ApolloClient
) {

    suspend fun createUser(idToken: String) {
        apolloClient.mutation(CreateSessionByGoogleIdTokenMutation(idToken)).execute()
    }
}