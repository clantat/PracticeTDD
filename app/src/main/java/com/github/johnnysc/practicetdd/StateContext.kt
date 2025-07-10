package com.github.johnnysc.practicetdd

interface StateContext {
    fun log(logger: Logging)
    fun next()
    interface Update {
        fun updateState(state: State)

    }

    abstract class Actions(protected var state: State) : StateContext, Update {
        override fun log(logger: Logging) {
            logger.log(state.javaClass.simpleName)
        }
    }

    class Base(state: State) : Actions(state) {

        override fun next() {
            state.next(this)
        }

        override fun updateState(state: State) {
            super.state = state
        }
    }
}