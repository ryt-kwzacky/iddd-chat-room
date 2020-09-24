package com.example.demo.domain

/**
 * This is an interface to extend to Enum class in domain layer.
 */
interface TypeList {
    /**
     * Value to persist.
     * In this application, we don't persist constant names in an enum class directly,
     * and persist this field value (identifier) instead.
     * This value is also used when restore and frontend side.
     *
     * By this, we can change constant names without dependent persisted values.
     */
    val identifier: String

    companion object {
        private class NotFoundIdentifier(override val message: String) : Exception()

        /**
         * Method used from `identifierOf` method in enum classes which are extended this interface.
         * `identifierOf` is a method to find a constant in enum class by identifier value
         * like `valueOf` which is standard method for enum class in Kotlin.
         * So `identifierOf` is defined in companion object.
         *
         * e.g.
         * ```
         * fun identifierOf(identifier: String): LivingPlaceType =
         *     TypeList.findByIdentifier<LivingPlaceType>(LivingPlaceType.values(), identifier)
         * ```
         */
        fun <T : TypeList> findByIdentifier(list: Array<T>, identifier: String): T =
            list.find { it.identifier == identifier } ?: throw NotFoundIdentifier("$identifier is not found.")
    }
}

