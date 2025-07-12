package com.github.johnnysc.practicetdd

interface LoginRepository {

    suspend fun login(credentials: LoginCredentials): LoginResult

    class Base() : LoginRepository {
        override suspend fun login(credentials: LoginCredentials): LoginResult {
            TODO("Not yet implemented")
        }

    }
}