package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class DeleteRoomCommand private constructor(
    val universalUserId: UniversalUserId,
    val roomId: RoomId
) {
    companion object {
        fun create(
            universalUserId: String,
            roomId: String
        ) = DeleteRoomCommand(
            universalUserId = UniversalUserId(universalUserId),
            roomId = RoomId(roomId)
        )
    }
}
