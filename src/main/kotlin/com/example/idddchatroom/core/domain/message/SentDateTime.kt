package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject
import java.time.ZonedDateTime

class SentDateTime(private val value: ZonedDateTime): ValueObject<SentDateTime.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
