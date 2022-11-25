package org.michaelbel.movies.domain.repository.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.michaelbel.movies.domain.datasource.ktx.PREFERENCE_DYNAMIC_COLORS_KEY
import org.michaelbel.movies.domain.datasource.ktx.PREFERENCE_RTL_ENABLED_KEY
import org.michaelbel.movies.domain.datasource.ktx.PREFERENCE_THEME_KEY
import org.michaelbel.movies.domain.datasource.ktx.orDefaultDynamicColorsEnabled
import org.michaelbel.movies.domain.datasource.ktx.orDefaultRtlEnabled
import org.michaelbel.movies.domain.datasource.ktx.orDefaultTheme
import org.michaelbel.movies.domain.repository.SettingsRepository
import org.michaelbel.movies.ui.theme.model.AppTheme

internal class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): SettingsRepository {

    override val currentTheme: Flow<AppTheme> = dataStore.data.map { preferences: Preferences ->
        return@map AppTheme.transform(preferences[PREFERENCE_THEME_KEY].orDefaultTheme())
    }

    override val dynamicColors: Flow<Boolean> = dataStore.data.map { preferences: Preferences ->
        return@map preferences[PREFERENCE_DYNAMIC_COLORS_KEY].orDefaultDynamicColorsEnabled()
    }

    override val rtlEnabled: Flow<Boolean> = dataStore.data.map { preferences: Preferences ->
        return@map preferences[PREFERENCE_RTL_ENABLED_KEY].orDefaultRtlEnabled()
    }

    override suspend fun selectTheme(theme: AppTheme) {
        dataStore.edit { preferences ->
            preferences[PREFERENCE_THEME_KEY] = theme.theme
        }
    }

    override suspend fun setDynamicColors(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PREFERENCE_DYNAMIC_COLORS_KEY] = value
        }
    }

    override suspend fun setRtlEnabled(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PREFERENCE_RTL_ENABLED_KEY] = value
        }
    }
}