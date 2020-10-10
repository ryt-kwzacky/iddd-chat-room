package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.domain.message.Message
import com.example.idddchatroom.core.domain.message.MessageId
import com.example.idddchatroom.core.domain.message.MessageRepository
import org.springframework.beans.factory.annotation.Autowired

//@Service
class CreateMessageCommandHandler(
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: CreateMessageCommand
    ): MessageId {
        val newMessage = Message.create(
            id = messageRepository.nextIdentity(),
            text = command.text,
            image = command.image,
            roomId = command.roomId,
            sender = command.sender,
            targetMessageId = null
        )
        messageRepository.store(newMessage)
        return newMessage.id
    }
}
