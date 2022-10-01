package org.michaelbel.movies.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.michaelbel.movies.details.ui.DetailsContent
import org.michaelbel.movies.feed.FeedContent
import org.michaelbel.movies.navigation.NavGraph
import org.michaelbel.movies.settings.ui.SettingsContent
import org.michaelbel.movies.ui.MoviesTheme

@Composable
fun MainActivityContent(
    onAppUpdateClicked: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    Scaffold { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavGraph.Home.route,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(
                route = NavGraph.Home.route
            ) {
                FeedContent(navController, onAppUpdateClicked)
            }
            composable(
                route = NavGraph.Movie.routeWithArgs,
                arguments = listOf(navArgument(NavGraph.Movie.argMovieId) {
                    type = NavType.LongType
                })
            ) { backStackEntry ->
                val movieId: Long? = backStackEntry.arguments?.getLong(NavGraph.Movie.argMovieId)
                if (movieId != null) {
                    DetailsContent(
                        navController = navController,
                        movieId = movieId
                    )
                }
            }
            composable(
                route = NavGraph.Settings.route
            ) {
                SettingsContent(navController)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainActivityContentPreview() {
    MoviesTheme {
        MainActivityContent(
            onAppUpdateClicked = {}
        )
    }
}