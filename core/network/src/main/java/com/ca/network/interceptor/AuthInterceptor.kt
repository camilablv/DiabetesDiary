package com.ca.network.interceptor

import com.ca.authentication.token.JWTService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val jwtService: JWTService) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getToken()

        val request =  chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }

    private fun getToken(): String {
        return runBlocking { jwtService.authToken() }
    }
}