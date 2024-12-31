package org.dwivedi.marketoutlook

import android.app.Application
import org.dwivedi.marketoutlook.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class MarketOutlookApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MarketOutlookApp)
         }
    }
}