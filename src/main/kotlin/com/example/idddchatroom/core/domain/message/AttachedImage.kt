package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * 添付画像
 */
class AttachedImage(private val value: String): ValueObject<AttachedImage.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
