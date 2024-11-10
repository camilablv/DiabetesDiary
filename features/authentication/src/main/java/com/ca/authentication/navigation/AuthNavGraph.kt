package com.ca.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.authentication.presentation.AuthScreen
import com.ca.model.TopLevelDestination
import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph : TopLevelDestination {
    @Serializable
    data object Login
}

fun NavGraphBuilder.authNavGraph(
    onComplete: () -> Unit
) {
    navigation<AuthGraph>(
        startDestination = AuthGraph.Login,
    ) {
        composable<AuthGraph.Login> {
            AuthScreen(
                onComplete = onComplete
            )
        }
    }
}