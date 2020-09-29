package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
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
    private val targetMessageId: MessageId?
) : Entity<Message.DTO>() {
    companion object {
        fun create(
            id: MessageId,
            text: MessageText,
            image: AttachedImage,
            roomId: RoomId,
            sender: MessageSender,
            targetMessageId: MessageId?
        ) = Message(
            id = id,
            text = text,
            image = image,
            roomId = roomId,
            sender = sender,
            sentDateTime = SentDateTime.genCreatedDateTime(),
            targetMessageId = targetMessageId
        )
    }

    fun edit(
        newText: MessageText,
        sender: MessageSender,
        currentDateTime: SentDateTime
    ): Message {
        require(
            // TODO: 権限管理をAOPでやる
            sender.isMatchedWith(this.sender) && currentDateTime.isWithinEditableTimeFrom(this.sentDateTime)
        )
        return copy(text = newText)
    }

    fun isReplyMessage(): Boolean = this.targetMessageId !== null

    private fun copy(
        text: MessageText = this.text,
        image: AttachedImage = this.image,
        roomId: RoomId = this.roomId,
        sender: MessageSender = this.sender,
        sentDateTime: SentDateTime = this.sentDateTime,
        targetMessageId: MessageId? = this.targetMessageId
    ) = Message(
        id = id,
        text = text,
        image = image,
        roomId = roomId,
        sender = sender,
        sentDateTime = sentDateTime,
        targetMessageId = targetMessageId
    )

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
        val targetMessageId: MessageId?
    )
}
