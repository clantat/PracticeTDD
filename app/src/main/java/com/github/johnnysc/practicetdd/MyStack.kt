package com.github.johnnysc.practicetdd

interface MyStack<T> {
    fun pop(): T
    fun push(item: T)
    class FIFO<T>(private val maxCount: Int) : MyStack<T> {

        init {
            if (maxCount < 1)
                throw IllegalStateException()
        }

        private val array: Array<Any?> = arrayOfNulls(maxCount)
        private var head = 0
        private var tail = 0
        private var size = 0


        override fun pop(): T {
            if (size == 0)
                throw IllegalStateException()
            val item = array[head] as T
            array[head] = null
            head = (head + 1) % maxCount
            size--
            return item
        }

        override fun push(item: T) {
            if (size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            array[tail] = item
            tail = (tail + 1) % maxCount
            size++
        }

    }

    class LIFO<T>(private val maxCount: Int) : MyStack<T> {

        init {
            if (maxCount < 1)
                throw IllegalStateException()
        }

        private val array: Array<Any?> = arrayOfNulls(maxCount)
        private var size = 0

        override fun pop(): T {
            if (size == 0)
                throw IllegalStateException()
            size--
            val item = array[size] as T
            array[size] = null
            return item
        }

        override fun push(item: T) {
            if (size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            array[size] = item
            size++
        }

    }
}
