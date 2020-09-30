package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.FindResult

interface RoomRepository {
    fun nextIdentity(): RoomId

    fun findById(id: RoomId): FindResult<Room>

    fun findAllByRoomOwner(roomOwner: RoomOwner): FindResult<Room>

    fun store(room: Room)

    fun remove(room: Room)
}
