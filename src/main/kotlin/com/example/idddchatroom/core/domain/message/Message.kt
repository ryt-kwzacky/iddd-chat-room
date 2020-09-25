package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.room.RoomId

class Message(
    val id: MessageId,
    private val text: MessageText,
    private val image: AttachedImage,
    private val roomId: RoomId,
    private val sender: MessageSender,
    private val sentDateTime: SentDateTime,
    private val targetMessageId: MessageId
) {
}
