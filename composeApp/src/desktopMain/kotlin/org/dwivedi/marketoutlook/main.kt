package org.dwivedi.marketoutlook

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.dwivedi.marketoutlook.app.App
import org.dwivedi.marketoutlook.di.platformModule
import org.dwivedi.marketoutlook.di.sharedModule
import org.koin.core.context.startKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Market Outlook",
    ) {

        startKoin {
            modules(
                sharedModule,  // Your shared KMP module
                platformModule // Platform-specific module for Desktop
            )
        }

        App()
    }
}