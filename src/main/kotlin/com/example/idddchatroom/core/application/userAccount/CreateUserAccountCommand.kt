package com.example.idddchatroom.core.application.userAccount

import com.example.idddchatroom.core.domain.userAccount.IconImage
import com.example.idddchatroom.core.domain.userAccount.UserName

data class CreateUserAccountCommand private constructor(
    val userName: UserName,
    val iconImage: IconImage
) {
    companion object {
        fun create(
            userName: String,
            iconImage: String
        ) = CreateUserAccountCommand(
            userName = UserName(userName),
            iconImage = IconImage(iconImage)
        )
    }
}
