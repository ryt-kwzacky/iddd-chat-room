package com.example.idddchatroom.dddFoundation

class FindAllResult<T>(private val entities: List<T>) {
    fun get(): List<T> = entities
    fun count(): Int = entities.count()
    fun exists(): Boolean = entities.count() > 0
}
