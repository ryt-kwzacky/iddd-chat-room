package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.dddFoundation.FindAllResult
import com.example.idddchatroom.dddFoundation.FindResult

interface MessageRepository {
    fun nextIdentity(): MessageId

    fun findById(id: MessageId): FindResult<Message>

    fun findAllByUniversalUserId(universalUserId: UniversalUserId): FindAllResult<Message>

    fun findAllByRoomId(roomId: RoomId): FindAllResult<Message>

    fun findSentLastByRoomId(roomId: RoomId): FindResult<Message>

    fun findAllByTargetMessageId(targetMessageId: MessageId): FindAllResult<Message>

    fun store(message: Message)

    fun remove(message: Message)
}
