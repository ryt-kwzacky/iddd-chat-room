package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.domain.room.RoomLevel
import com.example.idddchatroom.core.domain.room.RoomName
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class CreateRoomCommand private constructor(
    val ownerId: UniversalUserId,
    val roomName: RoomName,
    val roomLevel: RoomLevel
) {
    companion object {
        fun create(
            ownerId: String,
            roomName: String,
            roomLevel: Int
        ) = CreateRoomCommand(
            ownerId = UniversalUserId(ownerId),
            roomName = RoomName(roomName),
            roomLevel = RoomLevel(roomLevel)
        )
    }
}
