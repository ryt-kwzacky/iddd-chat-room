package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * メッセージテキスト
 */
class MessageText(private val value: String): ValueObject<MessageText.DTO> {
    companion object {
        const val MIN_LENGTH = 1
        const val MAX_LENGTH = 1000
    }

    init {
        validateFields()
    }

    private fun validateFields() {
        require(value.length in MIN_LENGTH..MAX_LENGTH) {
            "$value is invalid in ${this::class}"
        }
    }

    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
