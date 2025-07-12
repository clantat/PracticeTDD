package com.github.johnnysc.practicetdd

interface LoginInteractor {

    suspend fun login(credentials: LoginCredentials): LoginResult
    class Base(private val repository: LoginRepository) : LoginInteractor {
        var invalidCredentialsCount = 0
        override suspend fun login(credentials: LoginCredentials): LoginResult {
            try {
                if (invalidCredentialsCount >= INVALID_CREDENTIAL_MAX_COUNT)
                    return LoginResult.Block
                val loginResult = repository.login(credentials)
                return loginResult
            } catch (error: LoginResult.Error) {
                if (error is LoginResult.Error.IncorrectCredentials) {
                    if (invalidCredentialsCount < INVALID_CREDENTIAL_MAX_COUNT)
                        invalidCredentialsCount++
                }
                return error
            }
        }

        companion object {
            private const val INVALID_CREDENTIAL_MAX_COUNT = 3
        }
    }
}