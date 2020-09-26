package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.userAccount.IconImage
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.core.domain.userAccount.UserAccount
import com.example.idddchatroom.core.domain.userAccount.UserName
import java.util.*

object UserAccountFactory {
    fun genUserAccount(
        universalUserId: UniversalUserId,
        userName: UserName,
        iconImage: IconImage
    ): UserAccount = UserAccount.create(
        universalUserId = universalUserId,
        userName = userName,
        iconImage = iconImage
    )
    fun genUniversalUserId(): UniversalUserId = TestDataGenerator.genRandomUniversalUserId()
    fun genUserName(): UserName = UserName(TestDataGenerator.genRandomLengthString(range = 1..16))
    fun genIconImage(): IconImage {
        TODO()
    }
}
