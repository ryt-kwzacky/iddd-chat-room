package com.example.idddchatroom.core.application.message.command

import com.example.idddchatroom.core.domain.message.MessageId

class DeleteMessageCommand private constructor(
    val messageId: MessageId
){
    companion object {
        fun create(
            messageId: String
        ) = DeleteMessageCommand(
            messageId = MessageId(messageId)
        )
    }
}
