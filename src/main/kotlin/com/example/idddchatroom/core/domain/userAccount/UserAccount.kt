package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.dddFoundation.Entity

/**
 * ユビキタス言語:
 * ユーザーアカウント
 */
class UserAccount(
    override val id: UniversalUserId,
    private val userName: UserName,
    private val iconImage: IconImage
) : Entity<UserAccount.DTO>() {
    companion object {
        fun create(
            universalUserId: UniversalUserId,
            userName: UserName,
            iconImage: IconImage
        ) =
            UserAccount(
                id = universalUserId,
                userName = userName,
                iconImage = iconImage
            )
    }

    override fun toDTO(): DTO = DTO(
        id = id,
        userName = userName.toDTO(),
        icon = iconImage.toDTO()
    )

    data class DTO(
        val id: UniversalUserId,
        val userName: UserName.DTO,
        val icon: IconImage.DTO
    )
}
