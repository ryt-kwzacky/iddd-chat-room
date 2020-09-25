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

    override fun toDTO(): DTO = DTO(
        id = this.id,
        userName = userName.toDTO(),
        icon = iconImage.toDTO()
    )

    data class DTO(
        val id: UniversalUserId,
        val userName: UserName.DTO,
        val icon: IconImage.DTO
    )
}
