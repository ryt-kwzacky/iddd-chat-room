package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * ルームレベル
 */
class RoomLevel(private val value: Int): ValueObject<RoomLevel.DTO> {
    companion object {
        const val MIN_NUMBER = 0
        const val MAX_NUMBER = 100
    }

    init {
        validateFields()
    }

    private fun validateFields() {
        require(value in MIN_NUMBER..MAX_NUMBER) {
            "$value is invalid in ${this::class}"
        }
    }
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: Int)
}
