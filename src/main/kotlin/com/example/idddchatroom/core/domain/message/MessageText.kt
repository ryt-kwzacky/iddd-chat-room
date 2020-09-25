package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * メッセージテキスト
 */
class MessageText(private val value: String): ValueObject<MessageText.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
