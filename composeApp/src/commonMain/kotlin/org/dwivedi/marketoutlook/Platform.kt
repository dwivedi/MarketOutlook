package org.dwivedi.marketoutlook

import org.dwivedi.marketoutlook.core.data.Preferences

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


