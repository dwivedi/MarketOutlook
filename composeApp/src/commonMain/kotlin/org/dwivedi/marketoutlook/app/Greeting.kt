package org.dwivedi.marketoutlook.app

import org.dwivedi.marketoutlook.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}