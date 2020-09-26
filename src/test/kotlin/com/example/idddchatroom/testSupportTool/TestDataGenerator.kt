package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory
import org.apache.commons.lang3.RandomStringUtils

object TestDataGenerator {
    fun genRandomUniversalUserId(): UniversalUserId = UniversalUserId(RandomIdentityFactory.create())

    fun genRandomString(length: Int): String =
        RandomStringUtils.randomAlphanumeric(length)

    fun genRandomNumber(range: IntRange): Number = range.random()

    fun genRandomLengthString(range: IntRange): String = genRandomString(genRandomNumber(range).toInt())
}
