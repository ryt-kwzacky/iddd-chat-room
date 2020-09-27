package com.example.idddchatroom.core.domain.userAccount

import com.example.idddchatroom.dddFoundation.FindResult

interface UserAccountRepository {
    fun nextIdentity(): UniversalUserId

    fun findById(id: UniversalUserId): FindResult<UserAccount>

    fun store(userAccount: UserAccount)

    fun remove(userAccount: UserAccount)
}
