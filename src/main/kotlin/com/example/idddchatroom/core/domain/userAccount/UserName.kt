package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * ユーザー名
 */
class UserName(private val value: String): ValueObject<UserName.DTO> {
    companion object {
        const val MIN_LENGTH = 1
        const val MAX_LENGTH = 16
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
