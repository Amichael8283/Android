@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.michaelbel.movies.ui.preview.provider

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import org.michaelbel.movies.common.localization.model.AppLanguage

actual class LanguagePreviewParameterProvider: CollectionPreviewParameterProvider<AppLanguage>(
    AppLanguage.VALUES
)