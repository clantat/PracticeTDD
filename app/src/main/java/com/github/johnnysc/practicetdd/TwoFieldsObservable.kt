package com.github.johnnysc.practicetdd

class TwoFieldsObservable(private val observer: TwoFieldsObjectObserver) : Observable {
    override fun accept(id: Int) {
        TODO("Not yet implemented")
    }

    override fun accept(name: String) {
        TODO("Not yet implemented")
    }

}

interface Observable {
    fun accept(id: Int)
    fun accept(name: String)

}