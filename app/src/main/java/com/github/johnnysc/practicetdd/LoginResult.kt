package com.github.johnnysc.practicetdd

interface LoginResult {

    data object Success : LoginResult

    data object Block : LoginResult

    abstract class Error : LoginResult, Throwable() {
        data object IncorrectCredentials : Error() {
            private fun readResolve(): Any = IncorrectCredentials
        }

        data object NoInternet : Error() {
            private fun readResolve(): Any = NoInternet
        }

        data object ServerUnavailable : Error() {
            private fun readResolve(): Any = ServerUnavailable
        }
    }
}