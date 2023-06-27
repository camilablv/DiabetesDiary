package com.ca.network.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.ca.CreateInsulinMutation
import com.ca.CreateSessionByGoogleIdTokenMutation
import com.ca.UpdateGlucoseUnitMutation
import com.ca.model.GlucoseUnits
import com.ca.network.error.NetworkErrorHandler
import com.ca.type.BloodGlucoseUnits
import com.ca.type.SettingsInput
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

    suspend fun updateGlucoseUnit(unit: GlucoseUnits): Result<UpdateGlucoseUnitMutation.Data> {
        val glucoseUnit = BloodGlucoseUnits.safeValueOf(unit.unit)

        return errorHandler.withErrorHandler {
            apolloClient.mutation(
                UpdateGlucoseUnitMutation(
                    SettingsInput(
                        Optional.Present(
                            glucoseUnit
                        )
                    )
                )
            ).execute()
        }
    }

    suspend fun createInsulin(name: String, color: String, defaultDose: Int): Result<CreateInsulinMutation.Data> {
        return errorHandler.withErrorHandler {
            apolloClient.mutation(CreateInsulinMutation(name, color, defaultDose)).execute()
        }
    }

    suspend fun isOnBoardingShowed(): Boolean {
        return false
    }
}