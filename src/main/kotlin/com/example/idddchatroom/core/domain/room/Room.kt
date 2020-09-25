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
    companion object {
        fun create(
            roomId: RoomId,
            roomName: RoomName,
            roomLevel: RoomLevel,
            ownerId: RoomOwner
        ) = Room(
            id = roomId,
            name = roomName,
            level = roomLevel,
            ownerId = ownerId
        )
    }

    fun updateRoomLevel(newRoomLevel: RoomLevel): Room {
        require(newRoomLevel.isLowerThan(this.level))
        return copy(level = newRoomLevel)
    }

    private fun copy(
        name: RoomName = this.name,
        level: RoomLevel = this.level,
        ownerId: RoomOwner = this.ownerId
    ) = Room(
        id = id,
        name = name,
        level = level,
        ownerId = ownerId
    )

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
