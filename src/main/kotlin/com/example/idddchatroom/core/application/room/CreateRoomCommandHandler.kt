package com.example.idddchatroom.core.application.room

import com.example.idddchatroom.core.application.room.command.CreateRoomCommand
import com.example.idddchatroom.core.domain.message.MessageRepository
import com.example.idddchatroom.core.domain.room.Room
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.room.RoomRepository
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class CreateRoomCommandHandler(
    @Autowired private val roomRepository: RoomRepository,
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: CreateRoomCommand
    ): RoomId {
        // TODO: ロール与えてAOPで処理するようにする
        // TODO: 複雑なのでspecificationクラスを用意
        // 一つ以上メッセージを送っていることをチェック
        if (!messageRepository.findAllByUniversalUserId(command.ownerId.toDTO().value).exists()) {
            // 一つ以上ルームを作成していることをチェック
            if (roomRepository.findAllByRoomOwner(command.ownerId).exists()) {
                throw IllegalStateException()
            }
        }

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
