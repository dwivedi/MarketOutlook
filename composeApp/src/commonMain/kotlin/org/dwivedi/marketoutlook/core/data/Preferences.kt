package org.dwivedi.marketoutlook.core.data

interface Preferences {
    fun putString(key: String, value: String?)
    fun getString(key: String): String?
}