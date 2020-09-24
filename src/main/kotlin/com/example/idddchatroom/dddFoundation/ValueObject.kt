package com.example.idddchatroom.dddFoundation

interface ValueObject<DTO> {
    fun toDTO(): DTO
}
