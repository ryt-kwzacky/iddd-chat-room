package com.example.idddchatroom.core.domain.message

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
        const val MESSAGE_EDITABLE_TIME_LIMIT = 15 * 60

        // 60分（60 * 60秒）
        const val ROOM_DELETABLE_TIME_LIMIT = 60 * 60
    }

    fun meetsRequirementToEdit(): Boolean =
        Duration.between(this.value, ZonedDateTime.now()).toSeconds() < MESSAGE_EDITABLE_TIME_LIMIT

    fun meetsRequirementToDeleteRoom(): Boolean =
        Duration.between(this.value, ZonedDateTime.now()).toSeconds() > ROOM_DELETABLE_TIME_LIMIT

    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: ZonedDateTime)
}
