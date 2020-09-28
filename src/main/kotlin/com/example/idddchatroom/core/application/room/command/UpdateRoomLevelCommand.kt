package com.example.idddchatroom.core.application.room.command

import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.room.RoomLevel

data class UpdateRoomLevelCommand private constructor(
    val roomId: RoomId,
    val newRoomLevel: RoomLevel
) {
    companion object {
        fun create(
            roomId: String,
            newRoomLevel: Int
        ) = UpdateRoomLevelCommand(
            roomId = RoomId(roomId),
            newRoomLevel = RoomLevel(newRoomLevel)
        )
    }
}
