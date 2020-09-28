package com.example.idddchatroom.unit.application.room

import com.example.idddchatroom.core.application.room.CreateRoomCommandHandler
import com.example.idddchatroom.core.application.room.command.CreateRoomCommand
import com.example.idddchatroom.core.domain.room.RoomLevel
import com.example.idddchatroom.core.domain.room.RoomName
import com.example.idddchatroom.core.infra.room.db.InMemoryRoomRepository
import com.example.idddchatroom.core.infra.userAccount.db.InMemoryUserAccountRepository
import com.example.idddchatroom.testSupportTool.TestDataGenerator
import com.example.idddchatroom.testSupportTool.UserAccountFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CreateRoomCommandHandlerTests {
    private val roomRepository = InMemoryRoomRepository()
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val commandHandler = CreateRoomCommandHandler(
        roomRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val roomName = RoomName(TestDataGenerator.genRandomLengthString(1..16))
    private val roomLevel = RoomLevel(TestDataGenerator.genRandomInt(0..100))

    @Before
    fun setUp() {
        roomRepository.reset()
        userAccountRepository.reset()
        userAccountRepository.store(userAccount)
    }

    @Test
    fun `handle - create Room`() {
        val command = CreateRoomCommand.create(
            roomName = roomName.toDTO().value,
            roomLevel = roomLevel.toDTO().value,
            ownerId = universalUserId.value
        )

        /**
         * Before
         */
        // UserAccount
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(userAccountRepository.findById(universalUserId).exists()).isTrue()

        // Room
        assertThat(roomRepository.count()).isEqualTo(0)

        /**
         * Perform commandHandler
         */
        val createdRoomId = commandHandler.handle(command)

        /**
         * After
         */
        // UserAccount
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(userAccountRepository.findById(universalUserId).exists()).isTrue()

        // Room
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(roomRepository.findById(createdRoomId).exists()).isTrue()
        assertThat(roomRepository.findById(createdRoomId).getOrFail().toDTO().name.value).isEqualTo(roomName.toDTO().value)
        assertThat(roomRepository.findById(createdRoomId).getOrFail().toDTO().level.value).isEqualTo(roomLevel.toDTO().value)
    }
}
