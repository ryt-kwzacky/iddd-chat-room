package com.example.idddchatroom.core.infra.userAccount.db

import com.example.idddchatroom.core.domain.userAccount.*
import com.example.idddchatroom.dddFoundation.FindResult
import com.example.idddchatroom.sharedKernel.InMemoryBaseRepository
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory

class InMemoryUserAccountRepository : UserAccountRepository {
    private val repository = InMemoryBaseRepository<UserAccount>()

    fun reset() {
        repository.reset()
    }

    fun count() = repository.count()

    override fun nextIdentity(): UniversalUserId = UniversalUserId(RandomIdentityFactory.create())

    override fun findById(id: UniversalUserId): FindResult<UserAccount> =
        FindResult(
            repository.findBy { it.toDTO().id == id }
        )

    override fun store(userAccount: UserAccount) {
        val dto = userAccount.toDTO()
        repository.store(
            UserAccount(
                id = dto.id,
                userName = UserName(dto.userName.value),
                iconImage = IconImage(dto.icon.path)
            )
        )
    }

    override fun remove(userAccount: UserAccount) {
        val dto = userAccount.toDTO()
        repository.remove(
            UserAccount(
                id = dto.id,
                userName = UserName(dto.userName.value),
                iconImage = IconImage(dto.icon.path)
            )
        )
    }
}
