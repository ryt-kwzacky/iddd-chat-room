package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.application.room.command.CreateRoomCommand
import com.example.idddchatroom.core.domain.room.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class CreateRoomCommandHandler(
    @Autowired private val roomRepository: RoomRepository
) {
    fun handle(
        command: CreateRoomCommand
    ): RoomId {
        val newRoom = Room.create(
            roomId = roomRepository.nextIdentity(),
            roomName = command.roomName,
            roomLevel = command.roomLevel,
            ownerId = command.ownerId
        )
        roomRepository.store(newRoom)
        return newRoom.id
    }
}
