package org.michaelbel.movies.settings.about.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.michaelbel.movies.settings.about.impl.AboutManagerImpl
import org.michaelbel.movies.settings.about.AboutManager

actual val aboutKoinModule = module {
    singleOf(::AboutManagerImpl) { bind<AboutManager>() }
}