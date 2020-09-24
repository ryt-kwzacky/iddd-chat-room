package com.example.idddchatroom.sharedKernel

import org.apache.commons.lang3.RandomStringUtils

object RandomIdentityFactory {
    private const val IDENTIFY_LENGTH = 20
    fun create(): String = RandomStringUtils.randomAlphanumeric(IDENTIFY_LENGTH)
}
