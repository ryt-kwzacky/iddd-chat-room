package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.core.domain.room.CreatedDateTime
import com.example.idddchatroom.dddFoundation.ValueObject
import java.time.Duration
import java.time.ZonedDateTime

/**
 * ユビキタス言語:
 * 送信日時
 */
class SentDateTime(private val value: ZonedDateTime) : ValueObject<SentDateTime.DTO> {
    companion object {
        fun getSentDateTime() = SentDateTime(ZonedDateTime.now())

        // 15分（15 * 60秒）
        const val EDITABLE_TIME_LIMIT = 15 * 60

        // 60分（60 * 60秒）
        const val ROOM_DELETABLE_TIME_LIMIT = 60 * 60
    }

    fun isWithinEditableTimeFrom(sentDateTime: SentDateTime): Boolean =
        Duration.between(sentDateTime.value, this.value).toSeconds() < EDITABLE_TIME_LIMIT

    fun hasPassedEnoughSince(sentDateTime: SentDateTime): Boolean =
        Duration.between(sentDateTime.value, this.value).toSeconds() > SentDateTime.ROOM_DELETABLE_TIME_LIMIT

    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
