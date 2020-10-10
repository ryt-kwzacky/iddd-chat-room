package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.application.room.command.DeleteRoomCommand
import com.example.idddchatroom.core.domain.message.MessageRepository
import com.example.idddchatroom.core.domain.room.RoomRepository
import com.example.idddchatroom.core.domain.room.RoomSpecification
import org.springframework.beans.factory.annotation.Autowired

//@Service
class DeleteRoomCommandHandler(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val specification: RoomSpecification
) {
    fun handle(
        command: DeleteRoomCommand
    ) {
        // ルームが消えた時どうするの？の条件
        if (!specification.isDeletableRoom(
                universalUserId = command.universalUserId,
                roomId = command.roomId)
        ) {
            throw IllegalStateException()
        }

        val targetRoom = roomRepository.findById(command.roomId).getOrFail()
        roomRepository.remove(targetRoom)
    }
}
