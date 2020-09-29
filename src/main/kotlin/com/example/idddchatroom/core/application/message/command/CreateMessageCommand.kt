package com.example.idddchatroom.core.application.message.command

import com.example.idddchatroom.core.domain.message.*
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class CreateMessageCommand private constructor(
    val text: MessageText,
    val image: AttachedImage?,
    val roomId: RoomId,
    val sender: MessageSender
) {
    companion object {
        fun create(
            text: String,
            image: String?,
            roomId: String,
            sender: String
        ) = CreateMessageCommand(
            text = MessageText(text),
            image = image?.let { AttachedImage(it) },
            roomId = RoomId(roomId),
            sender = MessageSender(UniversalUserId(sender))
        )
    }
}
