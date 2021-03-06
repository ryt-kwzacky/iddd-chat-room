package com.example.idddchatroom.core.application.message

import com.example.idddchatroom.core.domain.message.Message
import com.example.idddchatroom.core.domain.message.MessageId
import com.example.idddchatroom.core.domain.message.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import java.lang.IllegalStateException

//@Service
class CreateReplyMessageCommandHandler(
    @Autowired private val messageRepository: MessageRepository
) {
    fun handle(
        command: CreateReplyMessageCommand
    ): MessageId {
        // TODO: 仕様クラスなどに切り分ける
        // リプライメッセージにリプライしようとすると例外を投げる処理
        val targetMessage = messageRepository.findById(command.targetMessageId).getOrFail()
        if (targetMessage.isReplyMessage()) {
            throw IllegalStateException()
        }

        val newMessage = Message.create(
            id = messageRepository.nextIdentity(),
            text = command.text,
            image = command.image,
            roomId = command.roomId,
            sender = command.sender,
            targetMessageId = command.targetMessageId
        )
        messageRepository.store(newMessage)
        return newMessage.id
    }
}
