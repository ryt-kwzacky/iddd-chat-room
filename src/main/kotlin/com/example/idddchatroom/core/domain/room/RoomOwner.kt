package com.example.idddchatroom.core.domain.room

import com.example.idddchatroom.core.domain.message.MessageSender
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.dddFoundation.ValueObject

/**
 * ユビキタス言語:
 * ルームオーナー
 */
class RoomOwner(private val value: UniversalUserId): ValueObject<RoomOwner.DTO> {
    fun isOwner(user: RoomOwner): Boolean = this.value == user.value

    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: UniversalUserId)
}
