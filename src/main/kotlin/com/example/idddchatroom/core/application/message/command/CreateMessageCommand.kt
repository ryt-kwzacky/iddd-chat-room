package com.example.idddchatroom.core.application.message.command

import com.example.idddchatroom.core.domain.message.*
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class CreateMessageCommand private constructor(
    val text: MessageText,
    val image: AttachedImage,
    val roomId: RoomId,
    val sender: MessageSender,
    val targetMessageId: MessageId?
) {
    companion object {
        fun create(
            text: String,
            image: String,
            roomId: String,
            sender: String,
            targetMessageId: String?
        ) = CreateMessageCommand(
            text = MessageText(text),
            image = AttachedImage(image),
            roomId = RoomId(roomId),
            sender = MessageSender(UniversalUserId(sender)),
            targetMessageId = targetMessageId?.let { MessageId(it) }
        )
    }
}
