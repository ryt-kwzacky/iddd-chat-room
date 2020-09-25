package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.Entity

/**
 * ユビキタス言語:
 * ルーム
 */
class Room(override val id: RoomId,
           private val level: RoomLevel,
           private val ownerId: RoomOwner
) : Entity<Room.DTO>() {
    override fun toDTO(): DTO = DTO(
        id = id,
        level = level.toDTO(),
        ownerId = ownerId.toDTO()
    )

    data class DTO(
        val id: RoomId,
        val level: RoomLevel.DTO,
        val ownerId: RoomOwner.DTO
    )
}
