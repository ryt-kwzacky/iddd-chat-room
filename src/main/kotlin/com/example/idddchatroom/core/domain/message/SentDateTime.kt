package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject
import java.time.ZonedDateTime

/**
 * ユビキタス言語:
 * 送信日時
 */
class SentDateTime(private val value: ZonedDateTime): ValueObject<SentDateTime.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
