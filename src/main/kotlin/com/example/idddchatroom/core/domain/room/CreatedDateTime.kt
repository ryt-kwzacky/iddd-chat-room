package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.dddFoundation.ValueObject
import java.time.Duration
import java.time.ZonedDateTime

class CreatedDateTime(private val value: ZonedDateTime): ValueObject<CreatedDateTime.DTO> {
    companion object {
        fun genCreatedDateTime() = CreatedDateTime(ZonedDateTime.now())

        // 60分（60 * 60秒）
        const val DELETABLE_TIME_LIMIT = 60 * 60
    }

    fun hasPassedEnoughSince(createdDateTime: CreatedDateTime): Boolean =
        Duration.between(createdDateTime.value, this.value).toSeconds() > DELETABLE_TIME_LIMIT

    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
