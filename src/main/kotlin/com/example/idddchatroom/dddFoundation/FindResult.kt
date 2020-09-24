package com.example.demo.domain

import java.lang.IllegalStateException

class FindResult<T>(private val entity: T?) {
    fun getOrNull(): T? = entity
    fun getOrFail(): T = entity ?: throw IllegalStateException()
    fun exists(): Boolean = entity != null
}
