package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.dddFoundation.Entity

/**
 * ユビキタス言語:
 * メッセージ
 */
class Message(
    override val id: MessageId,
    private val text: MessageText,
    private val image: AttachedImage,
    private val roomId: RoomId,
    private val sender: MessageSender,
    private val sentDateTime: SentDateTime,
    private val targetMessageId: MessageId
) : Entity<Message.DTO>() {
    override fun toDTO(): DTO = DTO(
        id = id,
        text = text.toDTO(),
        image = image.toDTO(),
        roomId = roomId,
        sender = sender.toDTO(),
        sentDateTime = sentDateTime.toDTO(),
        targetMessageId = targetMessageId
    )

    data class DTO(
        val id: MessageId,
        val text: MessageText.DTO,
        val image: AttachedImage.DTO,
        val roomId: RoomId,
        val sender: MessageSender.DTO,
        val sentDateTime: SentDateTime.DTO,
        val targetMessageId: MessageId
    )
}
