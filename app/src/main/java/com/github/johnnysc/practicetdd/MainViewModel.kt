package com.github.johnnysc.practicetdd

class MainViewModel(private val liveData: MyObservable<String>) {
    private var count = 0
    fun updateObserver(observer: MyObserver<String>) {
        liveData.updateObserver(observer)
    }

    fun go() {
        ++count
        liveData.update(count.toString())
    }

    fun notifyChanges() {
        liveData.notifyChanges()
    }
}