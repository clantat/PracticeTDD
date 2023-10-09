package com.github.johnnysc.practicetdd

interface MyObserver<T> : Update<T> {
    class Empty<T> : MyObserver<T> {
        override fun update(value: T) {

        }
    }
}

interface Update<T> {
    fun update(value: T)
}