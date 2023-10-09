package com.github.johnnysc.practicetdd

interface MyObservable<T> : Update<T> {
    fun updateObserver(observer: MyObserver<T>)
    fun notifyChanges(value: T)

    class SingleLiveEvent<T> : MyObservable<T> {
        private var observer: MyObserver<T> = MyObserver.Empty()
        private var previousValue: T? = null
        override fun update(value: T) {
            observer.update(value)
        }

        override fun updateObserver(observer: MyObserver<T>) {
            this.observer = observer
        }

        override fun notifyChanges(value: T) {
            if (previousValue != value) {
                observer.update(value)
                previousValue = value
            }

        }

    }

    class Base<T> : MyObservable<T> {
        private var observer: MyObserver<T> = MyObserver.Empty()

        override fun updateObserver(observer: MyObserver<T>) {
            this.observer = observer
        }

        override fun notifyChanges(value: T) {
            observer.update(value)
        }

        override fun update(value: T) {
            observer.update(value)
        }

    }
}