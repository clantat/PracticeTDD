package com.github.johnnysc.practicetdd

interface MyObservable<T> : Update<T> {
    fun updateObserver(observer: MyObserver<T>)
    fun notifyChanges()

    class SingleLiveEvent<T> : MyObservable<T> {
        private var observer: MyObserver<T> = MyObserver.Empty()
        private var value: T? = null
        private var previousValue: T? = null
        override fun update(value: T) {
            this.value = value
            observer.update(value)
        }

        override fun updateObserver(observer: MyObserver<T>) {
            this.observer = observer
        }

        override fun notifyChanges() {
            if (previousValue != value) {
                previousValue = value
                previousValue?.let {
                    observer.update(it)
                }
            }
        }

    }

    class Base<T> : MyObservable<T> {
        private var observer: MyObserver<T> = MyObserver.Empty()
        private var value: T? = null

        override fun updateObserver(observer: MyObserver<T>) {
            this.observer = observer
        }

        override fun notifyChanges() {
            value?.let {
                observer.update(it)
            }
        }

        override fun update(value: T) {
            this.value = value
            observer.update(value)
        }

    }
}