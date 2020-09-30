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
        return if (universalUserId == roomRepository.findById(roomId).getOrFail().toDTO().ownerId.value) {
            // ルームの作成者の場合
            !messageRepository.findAllByRoomId(roomId).exists()
        } else {
            // ルームの作成者でない場合
            this.hasPassedEnoughToDeleteRoomSinceRoomCreated(roomId) &&
                this.hasPassedEnoughToDeleteRoomSinceLastMessageSent(roomId)
        }
    }

    private fun hasPassedEnoughToDeleteRoomSinceRoomCreated(roomId: RoomId): Boolean {
        val roomCurrentDateTime = CreatedDateTime.getCreatedDateTime()
        val roomCreatedDateTime =
            CreatedDateTime(roomRepository.findById(roomId).getOrFail().toDTO().createdDateTime.value)
        return roomCurrentDateTime.hasPassedEnoughToDeleteRoomSince(roomCreatedDateTime)
    }

    private fun hasPassedEnoughToDeleteRoomSinceLastMessageSent(roomId: RoomId): Boolean {
        val messageCurrentDateTime = SentDateTime.getSentDateTime()
        val messageSentDateTime =
            SentDateTime(messageRepository.findSentLastByRoomId(roomId).getOrFail().toDTO().sentDateTime.value)
        return messageCurrentDateTime.hasPassedEnoughToDeleteRoomSince(messageSentDateTime)
    }
}
