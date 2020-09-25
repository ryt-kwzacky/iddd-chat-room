package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * ユーザー名
 */
class UserName(private val value: String): ValueObject<UserName.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
