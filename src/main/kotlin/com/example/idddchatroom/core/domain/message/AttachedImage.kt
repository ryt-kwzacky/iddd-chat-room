package com.example.idddchatroom.core.domain.message

import com.example.idddchatroom.dddFoundation.ValueObject

class AttachedImage(private val value: String): ValueObject<AttachedImage.DTO> {
    override fun toDTO(): DTO = DTO(value = value)

    data class DTO(val value: String)
}
