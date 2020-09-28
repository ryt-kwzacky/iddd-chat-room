package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.FindResult

interface RoomRepository {
    fun nextIdentity(): RoomId

    fun findById(id: RoomId): FindResult<Room>

    fun store(userAccount: Room)

    fun remove(userAccount: Room)
}
