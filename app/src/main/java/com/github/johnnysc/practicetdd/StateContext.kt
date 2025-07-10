package com.github.johnnysc.practicetdd

interface StateContext {

    interface Update {
        fun updateState(state: State)

    }
    interface Actions {
        fun log(logger: Logging)
        fun next()
    }

    class Base(private var state: State) : Actions, Update {

        override fun log(logger: Logging) {
            logger.log(state.javaClass.simpleName)
        }
        override fun next() {
            state.next(this)
        }

        override fun updateState(state: State) {
            this.state = state
        }
    }
}