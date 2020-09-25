package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * ルームレベル
 */
class RoomLevel(private val value: Int): ValueObject<RoomLevel.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: Int)
}
