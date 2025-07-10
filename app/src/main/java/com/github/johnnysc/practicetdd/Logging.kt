package com.github.johnnysc.practicetdd

interface Logging {
    fun log(event: String)

    object Empty : Logging {
        override fun log(event: String) = Unit
    }
}