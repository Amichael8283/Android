package org.michaelbel.moviemade.presentation.features.main.adapter

import org.michaelbel.data.remote.model.Movie

data class UiState(
    val list: String = Movie.NOW_PLAYING,
    val hasNotScrolledForCurrentSearch: Boolean = false
)