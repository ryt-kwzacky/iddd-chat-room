package com.example.idddchatroom.sharedKernel

class InMemoryBaseRepository<T> {
    private val entities: MutableList<T> = mutableListOf()

    fun reset() {
        entities.clear()
    }

    fun count(): Int = entities.count()

    fun count(predicate: (it: T) -> Boolean): Int = entities.count(predicate)

    fun findBy(predicate: (it: T) -> Boolean): T? = entities.find(predicate)

    fun findLastBy(predicate: (it: T) -> Boolean): T? = entities.findLast(predicate)

    fun findAllBy(predicate: (it: T) -> Boolean): List<T> = entities.filter(predicate).toList()

    fun store(entity: T) {
        val currentEntity = entities.find { it == entity }
        if (currentEntity != null) {
            remove(currentEntity)
        }
        entities.add(entity)
    }

    fun remove(entity: T) {
        entities.remove(entity)
    }
}
