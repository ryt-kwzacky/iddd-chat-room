package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.domain.message.*
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class CreateReplyMessageCommand private constructor(
    val text: MessageText,
    val image: AttachedImage?,
    val roomId: RoomId,
    val sender: MessageSender,
    val targetMessageId: MessageId
) {
    companion object {
        fun create(
            text: String,
            image: String?,
            roomId: String,
            sender: String,
            targetMessageId: String
        ) = CreateReplyMessageCommand(
            text = MessageText(text),
            image = image?.let { AttachedImage(it) },
            roomId = RoomId(roomId),
            sender = MessageSender(UniversalUserId(sender)),
            targetMessageId = MessageId(targetMessageId)
        )
    }
}
