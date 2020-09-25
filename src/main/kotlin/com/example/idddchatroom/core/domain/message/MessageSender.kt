package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * 送信者
 */
class MessageSender(private val value: UniversalUserId): ValueObject<MessageSender.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: UniversalUserId)
}
