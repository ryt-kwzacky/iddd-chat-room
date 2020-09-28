package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory
import org.apache.commons.lang3.RandomStringUtils
import java.util.*

object TestDataGenerator {
    fun genRandomUniversalUserId(): UniversalUserId = UniversalUserId(RandomIdentityFactory.create())

    fun genRandomString(length: Int): String =
        RandomStringUtils.randomAlphanumeric(length)

    fun genRandomNumber(range: IntRange): Number = range.random()

    fun genRandomInt(range: IntRange): Int = genRandomNumber(range).toInt()

    fun genRandomLengthString(range: IntRange): String = genRandomString(genRandomNumber(range).toInt())

    fun genRandomFileName(): String = UUID.randomUUID().toString().replace("-", "")
}
