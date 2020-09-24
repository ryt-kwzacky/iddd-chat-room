package com.example.demo.domain

interface ValueObject<DTO> {
    fun toDTO(): DTO
}
