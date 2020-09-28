package com.example.idddchatroom.unit.application.userAccount

import com.example.idddchatroom.core.application.userAccount.CreateUserAccountCommandHandler
import com.example.idddchatroom.core.application.userAccount.command.CreateUserAccountCommand
import com.example.idddchatroom.core.infra.userAccount.db.InMemoryUserAccountRepository
import com.example.idddchatroom.testSupportTool.UserAccountFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CreateUserAccountCommandHandlerTests {
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val commandHandler = CreateUserAccountCommandHandler(
        userAccountRepository
    )

    private val userName = UserAccountFactory.genUserName()
    private val icon = UserAccountFactory.genIconImage()

    @Before
    fun setUp() {
        userAccountRepository.reset()
    }

    @Test
    fun `handle - create UserAccount`() {
        val command = CreateUserAccountCommand.create(
            userName = userName.toDTO().value,
            iconImage =icon.toDTO().path
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(0)

        /**
         * Perform commandHandler
         */
        val createdUserAccountId = commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(userAccountRepository.findById(createdUserAccountId).exists()).isTrue()
        assertThat(userAccountRepository.findById(createdUserAccountId).getOrFail().toDTO().id)
            .isEqualTo(createdUserAccountId)
        assertThat(userAccountRepository.findById(createdUserAccountId).getOrFail().toDTO().userName.value)
            .isEqualTo(userName.toDTO().value)
        assertThat(userAccountRepository.findById(createdUserAccountId).getOrFail().toDTO().icon.path)
            .isEqualTo(icon.toDTO().path)
    }
}
