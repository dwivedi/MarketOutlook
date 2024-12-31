package org.dwivedi.marketoutlook

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import org.dwivedi.marketoutlook.core.data.Preferences

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

