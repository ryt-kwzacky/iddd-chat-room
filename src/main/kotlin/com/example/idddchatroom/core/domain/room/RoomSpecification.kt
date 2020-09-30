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
            // ルームに「メッセージ」が１件もない場合削除可能
            !messageRepository.findAllByRoomId(roomId).exists()
        } else {
            // ルームの作成者でない場合
            val roomCurrentDateTime = CreatedDateTime.getCreatedDateTime()
            val roomCreatedDateTime = CreatedDateTime(roomRepository.findById(roomId).getOrFail().toDTO().createdDateTime.value)

            val messageCurrentDateTime = SentDateTime.getSentDateTime()
            val messageSentDateTime = SentDateTime(messageRepository.findSentLastByRoomId(roomId).getOrFail().toDTO().sentDateTime.value)

            // 「ルーム」が作成されてから規定時間以上経過しており
            roomCurrentDateTime.hasPassedEnoughToDeleteRoomSince(roomCreatedDateTime) &&
                // 最後に送信された「ルーム」内の「メッセージ」が規定時間（1時間）以上前のものの場合削除可能
                messageCurrentDateTime.hasPassedEnoughToDeleteRoomSince(messageSentDateTime)
        }
    }
}
