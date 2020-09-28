package com.example.idddchatroom.core.application.room.command

import com.example.idddchatroom.core.domain.room.RoomId

data class DeleteRoomCommand private constructor(
    val roomId: RoomId
) {
    companion object {
        fun create(
            roomId: String
        ) = DeleteRoomCommand(
            roomId = RoomId(roomId)
        )
    }
}
