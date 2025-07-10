package com.github.johnnysc.practicetdd

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable

class ComposeReviveViewModel(
    private val runAsync: RunAsync = RunAsync.Base(),
    private val repository: SimpleRepository = SimpleRepository.Base(),
    private val mapper: LoadResult.Mapper<UiState>,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val STATE_KEY = "STATE_KEY"
    }

    private val _state = savedStateHandle.getStateFlow(STATE_KEY, UiState.Loading)

    val state: StateFlow<UiState> = _state
    fun load() {
        runAsync.async(viewModelScope, {
            repository.data()
        }) {
            savedStateHandle[STATE_KEY] = it.map(mapper)
        }
    }

    fun retry() {
        savedStateHandle[STATE_KEY] = UiState.Loading
    }

}


interface UiState : Serializable {

    data object Loading : UiState {
        private fun readResolve(): Any = Loading

    }

}

interface LoadResult {
    fun <T : Serializable> map(mapper: Mapper<T>): T
    interface Mapper<T> {
        fun map(data: DateAndName): T
        fun map(error: Exception): T
    }
}

data class DateAndName(private val date: String, private val name: String)

interface SimpleRepository {
    suspend fun data(): LoadResult

    class Base() : SimpleRepository {
        override suspend fun data(): LoadResult {
            TODO("Not yet implemented")
        }

    }
}

interface DispatchersList {

    fun background(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher


    class Base : DispatchersList {
        override fun background() = Dispatchers.Default

        override fun ui() = Dispatchers.Main

    }
}

interface RunAsync {
    fun <T : Any> async(
        scope: CoroutineScope,
        background: suspend () -> T,
        ui: (T) -> Unit
    )

    class Base(private val dispatchersList: DispatchersList = DispatchersList.Base()) : RunAsync {

        override fun <T : Any> async(
            scope: CoroutineScope,
            background: suspend () -> T,
            ui: (T) -> Unit
        ) {
            scope.launch(dispatchersList.background()) {
                val result = background.invoke()
                withContext(dispatchersList.ui()) {
                    ui.invoke(result)
                }
            }
        }

    }
}