package com.ca.authentication.data.network

import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class NetworkClient @Inject constructor(
    private val apolloClient: ApolloClient
) {

    fun createUser() {
        //todo call mutation method
    }
}