package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.Entity

/**
 * ユビキタス言語:
 * ルーム
 */
class Room(override val id: RoomId,
           private val name: RoomName,
           private val level: RoomLevel,
           private val ownerId: RoomOwner
) : Entity<Room.DTO>() {
    override fun toDTO(): DTO = DTO(
        id = id,
        name = name.toDTO(),
        level = level.toDTO(),
        ownerId = ownerId.toDTO()
    )

    data class DTO(
        val id: RoomId,
        val name: RoomName.DTO,
        val level: RoomLevel.DTO,
        val ownerId: RoomOwner.DTO
    )
}
