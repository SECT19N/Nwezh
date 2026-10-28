package com.section.nwezh.di

import com.section.nwezh.data.di.dataModule
import com.section.nwezh.platform.di.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Starts Koin with every layer's modules. Called from the Android `Application` and from the iOS
 * app entry point, so alarms, receivers and widgets can resolve dependencies outside Compose.
 */
fun initializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModule, dataModule, domainModule, presentationModule)
    }
}
