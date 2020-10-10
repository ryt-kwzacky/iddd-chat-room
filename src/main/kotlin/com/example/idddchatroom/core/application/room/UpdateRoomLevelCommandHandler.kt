package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.domain.room.RoomRepository
import org.springframework.beans.factory.annotation.Autowired

//@Service
class UpdateRoomLevelCommandHandler(
    @Autowired private val roomRepository: RoomRepository
) {
    fun handle(
        command: UpdateRoomLevelCommand
    ) {
        val targetRoom = roomRepository.findById(command.roomId).getOrFail()
        val updatedRoom = targetRoom.updateRoomLevel(command.newRoomLevel)
        roomRepository.store(updatedRoom)
    }
}
