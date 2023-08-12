package com.github.johnnysc.practicetdd

interface MyStack<T> {
    fun pop(): T
    fun push(item: T)
    class FIFO<T>(private val maxCount: Int) : MyStack<T> {
        private val list: ArrayList<T> = arrayListOf()

        init {
            if (maxCount < 1)
                throw IllegalStateException()
        }

        override fun pop(): T {
            if (list.isEmpty())
                throw IllegalStateException()
            val item = list.first()
            list.remove(item)
            return item
        }

        override fun push(item: T) {
            if (list.size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            list.add(item)
        }

    }

    class LIFO<T>(private val maxCount: Int) : MyStack<T> {
        private val list: ArrayList<T> = arrayListOf()

        init {
            if (maxCount < 1)
                throw IllegalStateException()
        }

        override fun pop(): T {
            if (list.isEmpty())
                throw IllegalStateException()
            val item = list.last()
            list.remove(item)
            return item
        }

        override fun push(item: T) {
            if (list.size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            list.add(item)
        }

    }
}
