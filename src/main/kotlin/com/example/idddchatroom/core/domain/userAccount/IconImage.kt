package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * アイコンイメージ
 */
class IconImage(private val value: String): ValueObject<IconImage.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
