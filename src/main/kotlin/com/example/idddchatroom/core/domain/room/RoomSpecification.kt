package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.core.domain.message.MessageRepository
import com.example.idddchatroom.core.domain.message.SentDateTime
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

//@Component
class RoomSpecification(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val messageRepository: MessageRepository
) {
    fun isDeletableRoom(
        universalUserId: UniversalUserId,
        roomId: RoomId
    ): Boolean {
        val targetRoom = roomRepository.findById(roomId).getOrFail()
        return if (targetRoom.isCreatedBy(universalUserId)) {
            return !messageRepository.findAllByRoomId(roomId).exists()
        } else {
            targetRoom.meetsCreatedDateTimeRequirementToDelete() &&
                messageRepository.findSentLastByRoomId(roomId).getOrFail()
                    .meetsSentDateTimeRequirementToDeleteRoom()
        }
    }
}
