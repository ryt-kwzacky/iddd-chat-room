package com.example.idddchatroom.core.application.userAccount

import com.example.idddchatroom.core.application.userAccount.command.CreateUserAccountCommand
import com.example.idddchatroom.core.domain.userAccount.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateUserAccountCommandHandler(
    @Autowired private val userAccountRepository: UserAccountRepository
) {
    fun handle(
        command: CreateUserAccountCommand
    ): UniversalUserId {
        val newUserAccount = UserAccount.create(
            universalUserId = userAccountRepository.nextIdentity(),
            userName = command.userName,
            iconImage = command.iconImage
        )
        userAccountRepository.store(newUserAccount)
        return newUserAccount.id
    }
}
