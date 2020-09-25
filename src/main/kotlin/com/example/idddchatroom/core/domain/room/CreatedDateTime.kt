package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.ValueObject
import java.time.ZonedDateTime

class CreatedDateTime(private val value: ZonedDateTime): ValueObject<CreatedDateTime.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
