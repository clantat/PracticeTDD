package com.github.johnnysc.practicetdd

class TwoFieldsObservable(private val observer: TwoFieldsObjectObserver) : Observable {
    companion object {
        val obj = Object()
    }

    private var name = ""
    private var id = -1
    override fun accept(id: Int) =
        synchronized(obj) {
            this.id = id
            if (this.name != "") {
                observer.notify(TwoFieldsObject(this.name, this.id))
                this.name = ""
                this.id = -1
            }
        }

    override fun accept(name: String) =
        synchronized(obj) {
            this.name = name
            if (this.id != -1) {
                observer.notify(TwoFieldsObject(this.name, this.id))
                this.name = ""
                this.id = -1
            }
        }


}

interface Observable {
    fun accept(id: Int)
    fun accept(name: String)

}