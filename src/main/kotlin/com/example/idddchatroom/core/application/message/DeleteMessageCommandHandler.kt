package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.application.message.command.DeleteMessageCommand
import com.example.idddchatroom.core.domain.message.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

//@Service
class DeleteMessageCommandHandler(
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: DeleteMessageCommand
    ) {
        val targetMessage = messageRepository.findById(command.messageId).getOrFail()

        // TODO: 400台の適切なエラーを返すようにする
        if (messageRepository.findAllByTargetMessageId(targetMessage.id).exists()) {
            throw IllegalStateException()
        }

        messageRepository.remove(targetMessage)
    }
}
