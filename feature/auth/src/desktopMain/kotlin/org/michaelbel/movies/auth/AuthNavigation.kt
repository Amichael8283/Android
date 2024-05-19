package org.michaelbel.movies.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import org.michaelbel.movies.auth.ui.AuthRoute

fun NavGraphBuilder.authGraph(
    navigateBack: () -> Unit
) {
    dialog(
        route = AuthDestination.route,
    ) {
        AuthRoute(
            onBackClick = navigateBack
        )
    }
}