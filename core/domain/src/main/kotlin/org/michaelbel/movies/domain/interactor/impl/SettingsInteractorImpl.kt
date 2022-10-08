package org.michaelbel.movies.domain.interactor.impl

import android.app.NotificationManager
import android.os.Build
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import org.michaelbel.movies.analytics.Analytics
import org.michaelbel.movies.analytics.event.ChangeDynamicColorsEvent
import org.michaelbel.movies.analytics.event.SelectThemeEvent
import org.michaelbel.movies.core.coroutines.MainDispatcher
import org.michaelbel.movies.domain.interactor.SettingsInteractor
import org.michaelbel.movies.domain.repository.SettingsRepository
import org.michaelbel.movies.ui.SystemTheme

class SettingsInteractorImpl @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val settingsRepository: SettingsRepository,
    private val notificationManager: NotificationManager,
    private val analytics: Analytics
): SettingsInteractor {

    override val currentTheme: Flow<SystemTheme> = settingsRepository.currentTheme

    override val dynamicColors: Flow<Boolean> = settingsRepository.dynamicColors

    override val areNotificationsEnabled: Boolean
        get() = if (Build.VERSION.SDK_INT >= 24) {
            notificationManager.areNotificationsEnabled()
        } else {
            true
        }

    override suspend fun selectTheme(systemTheme: SystemTheme) = withContext(dispatcher) {
        settingsRepository.selectTheme(systemTheme)
        analytics.logEvent(SelectThemeEvent(systemTheme.toString()))
    }

    override suspend fun setDynamicColors(value: Boolean) = withContext(dispatcher) {
        settingsRepository.setDynamicColors(value)
        analytics.logEvent(ChangeDynamicColorsEvent(value))
    }
}