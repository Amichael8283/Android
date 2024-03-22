@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.michaelbel.movies.ui.preview.provider

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import org.michaelbel.movies.common.appearance.FeedView

actual class AppearancePreviewParameterProvider: CollectionPreviewParameterProvider<FeedView>(
    FeedView.VALUES
)