package com.github.johnnysc.practicetdd

interface Parser {

    fun parse(raw: String): List<Any>

    fun define(item: String): Any
    class Base(private val delimiter: String) : Parser {

        init {
            if (delimiter.isEmpty())
                throw IllegalStateException()
        }

        override fun parse(raw: String): List<Any> {
            val list = mutableListOf<Any>()
            if (raw.isEmpty() || raw == "\n")
                return emptyList()
            val splittedList = raw.split(delimiter)
            splittedList.forEach {
                list.add(define(it))
            }
            return list
        }

        override fun define(item: String): Any {
            when (item) {
                Int.MAX_VALUE ->
            }
            Number
            if (item == "true" || item == "false")
                return "here is some text"
        }
    }

}