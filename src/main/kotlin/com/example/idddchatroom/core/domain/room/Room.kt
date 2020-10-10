package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.dddFoundation.Entity

/**
 * ユビキタス言語:
 * ルーム
 */
class Room(override val id: RoomId,
           private val ownerId: UniversalUserId,
           private val name: RoomName,
           private val level: RoomLevel,
           private val createdDateTime: CreatedDateTime
) : Entity<Room.DTO>() {
    companion object {
        fun create(
            roomId: RoomId,
            ownerId: UniversalUserId,
            roomName: RoomName,
            roomLevel: RoomLevel
        ) = Room(
            id = roomId,
            ownerId = ownerId,
            name = roomName,
            level = roomLevel,
            createdDateTime = CreatedDateTime.getCreatedDateTime()
        )
    }

    fun updateRoomLevel(newRoomLevel: RoomLevel): Room {
        require(newRoomLevel.isLowerThan(this.level))
        return copy(level = newRoomLevel)
    }

    fun meetsCreatedDateTimeRequirementToDelete(): Boolean =
        createdDateTime.meetsRequirementToDeleteRoom()

    fun isCreatedBy(universalUserId: UniversalUserId): Boolean =
        this.ownerId == universalUserId

    private fun copy(
        ownerId: UniversalUserId = this.ownerId,
        name: RoomName = this.name,
        level: RoomLevel = this.level,
        createdDateTime: CreatedDateTime = this.createdDateTime
    ) = Room(
        id = id,
        ownerId = ownerId,
        name = name,
        level = level,
        createdDateTime = createdDateTime
    )

    override fun toDTO(): DTO = DTO(
        id = id,
        ownerId = ownerId,
        name = name.toDTO(),
        level = level.toDTO(),
        createdDateTime = createdDateTime.toDTO()
    )

    data class DTO(
        val id: RoomId,
        val ownerId: UniversalUserId,
        val name: RoomName.DTO,
        val level: RoomLevel.DTO,
        val createdDateTime: CreatedDateTime.DTO
    )
}
