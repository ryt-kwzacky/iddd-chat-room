package com.example.idddchatroom.core.application.room.command

import com.example.idddchatroom.core.domain.room.RoomLevel
import com.example.idddchatroom.core.domain.room.RoomName
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class CreateRoomCommand private constructor(
    val roomName: RoomName,
    val roomLevel: RoomLevel,
    val ownerId: RoomOwner
) {
    companion object {
        fun create(
            roomName: String,
            roomLevel: Int,
            ownerId: String
        ) = CreateRoomCommand(
            roomName = RoomName(roomName),
            roomLevel = RoomLevel(roomLevel),
            ownerId = RoomOwner(UniversalUserId(ownerId))
        )
    }
}