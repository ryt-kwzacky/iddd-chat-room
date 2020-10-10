package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.domain.message.MessageId
import com.example.idddchatroom.core.domain.message.MessageSender
import com.example.idddchatroom.core.domain.message.MessageText
import com.example.idddchatroom.core.domain.message.SentDateTime
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId

data class UpdateMessageCommand private constructor(
    val messageId: MessageId,
    val newText: MessageText,
    val sender: MessageSender,
    val currentDateTime: SentDateTime
) {
    companion object {
        fun create(
            messageId: String,
            newText: String,
            sender: String
        ) = UpdateMessageCommand(
            messageId = MessageId(messageId),
            newText = MessageText(newText),
            sender = MessageSender(UniversalUserId(sender)),
            currentDateTime = SentDateTime.getSentDateTime()
        )
    }
}
