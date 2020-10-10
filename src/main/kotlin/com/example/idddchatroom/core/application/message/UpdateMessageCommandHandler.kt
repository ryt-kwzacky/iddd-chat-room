package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.application.message.command.UpdateMessageCommand
import com.example.idddchatroom.core.domain.message.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class UpdateMessageCommandHandler(
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: UpdateMessageCommand
    ) {
        val targetMessage = messageRepository.findById(command.messageId).getOrFail()
        val editedMessage = targetMessage.edit(
            newText = command.newText,
            sender = command.sender
        )
        messageRepository.store(editedMessage)
    }
}
