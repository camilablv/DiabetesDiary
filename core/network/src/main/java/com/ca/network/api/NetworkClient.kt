package com.ca.network.api

import com.apollographql.apollo3.ApolloClient
import com.ca.*
import com.ca.model.GlucoseUnits
import com.ca.network.error.NetworkErrorHandler
import com.ca.type.BloodGlucoseUnits
import com.ca.type.GlucoseRecordStatus
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
        val glucoseUnit = BloodGlucoseUnits.safeValueOf(unit.name)

        return errorHandler.withErrorHandler {
            apolloClient.mutation(
                UpdateGlucoseUnitMutation(
                    SettingsInput(
                        glucoseUnit
                    )
                )
            ).execute()
        }
    }

    suspend fun createInsulin(
        name: String,
        color: String,
        defaultDose: Int
    ): Result<CreateInsulinMutation.Data> {
        return errorHandler.withErrorHandler {
            apolloClient.mutation(CreateInsulinMutation(name, color, defaultDose)).execute()
        }
    }

    suspend fun currentUser(): Result<CurrentUserQuery.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.query(CurrentUserQuery()).execute()
        }
    }

    suspend fun deleteInsulin(id: String): Result<DeleteInsulinMutation.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.mutation(DeleteInsulinMutation(id)).execute()
        }
    }

    suspend fun completeOnBoarding(completedAt: String): Result<CompleteOnBoardingMutation.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.mutation(CompleteOnBoardingMutation(completedAt))
                .execute()
        }
    }

    suspend fun recordInsulin(
        insulinId: String,
        note: String,
        dateTime: String,
        dose: Int
    ): Result<RecordInsulinMutation.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.mutation(
                RecordInsulinMutation(
                    insulinId,
                    note,
                    dateTime,
                    dose.toDouble()
                )
            ).execute()
        }
    }

    suspend fun recordGlucose(
        dateTime: String,
        note: String,
        mark: String,
        units: Int
    ): Result<RecordGlucoseMutation.Data> {

        val status = GlucoseRecordStatus.safeValueOf(mark)
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.mutation(
                RecordGlucoseMutation(
                    dateTime,
                    note,
                    status,
                    units.toDouble()
                )
            ).execute()
        }
    }

    suspend fun insulinRecords(cursor: String, limit: Int): Result<InsulinRecordsQuery.Data> {
        return errorHandler.withErrorHandler {
            return@withErrorHandler apolloClient.query(InsulinRecordsQuery(cursor, limit)).execute()
        }
    }
}