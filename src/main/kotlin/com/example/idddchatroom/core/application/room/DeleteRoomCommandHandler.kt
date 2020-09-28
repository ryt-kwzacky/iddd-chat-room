package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.application.room.command.DeleteRoomCommand
import com.example.idddchatroom.core.domain.message.MessageRepository
import com.example.idddchatroom.core.domain.room.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class DeleteRoomCommandHandler(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: DeleteRoomCommand
    ) {
        if (messageRepository.findAllByRoomId(command.roomId).exists()) {
            // TODO: 削除対象ルームにメッセージが一件以上ある時の処理
            return
        }
        val targetRoom = roomRepository.findById(command.roomId).getOrFail()
        roomRepository.remove(targetRoom)
    }
}
