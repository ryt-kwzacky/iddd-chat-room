package com.example.idddchatroom.unit.application.room

import com.example.idddchatroom.core.application.room.UpdateRoomLevelCommandHandler
import com.example.idddchatroom.core.application.room.command.UpdateRoomLevelCommand
import com.example.idddchatroom.core.domain.room.RoomLevel
import com.example.idddchatroom.core.domain.room.RoomOwner
import com.example.idddchatroom.core.infra.message.InMemoryMessageRepository
import com.example.idddchatroom.core.infra.room.db.InMemoryRoomRepository
import com.example.idddchatroom.core.infra.userAccount.db.InMemoryUserAccountRepository
import com.example.idddchatroom.testSupportTool.RoomFactory
import com.example.idddchatroom.testSupportTool.UserAccountFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UpdateRoomLevelCommandHandlerTests {
    private val roomRepository = InMemoryRoomRepository()
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val messageRepository = InMemoryMessageRepository()
    private val commandHandler = UpdateRoomLevelCommandHandler(
        roomRepository = roomRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val room = RoomFactory.genRoom(
        ownerId = RoomOwner(universalUserId)
    )
    private val roomId = room.id
    private val roomLevel = room.toDTO().level
    private val newRoomLevel = RoomLevel(room.toDTO().level.value - 1).toDTO()

    @Before
    fun setUp() {
        roomRepository.reset()
        userAccountRepository.reset()
        messageRepository.reset()
        userAccountRepository.store(userAccount)
        roomRepository.store(room)
    }

    @Test
    fun `handle - lower RoomLevel`() {
        val command = UpdateRoomLevelCommand.create(
            roomId = roomId.value,
            newRoomLevel = newRoomLevel.value
        )

        /**
         * Before
         */
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(roomRepository.findById(roomId).exists()).isTrue()
        assertThat(roomRepository.findById(roomId).getOrFail().toDTO().level).isEqualTo(roomLevel)
        assertThat(roomRepository.findById(roomId).getOrFail().toDTO().level.value).isEqualTo(roomLevel.value)

        /**
         * Perform commandHandler
         */
        commandHandler.handle(command)

        /**
         * After
         */
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(roomRepository.findById(roomId).exists()).isTrue()
        assertThat(roomRepository.findById(roomId).getOrFail().toDTO().level).isEqualTo(newRoomLevel)
        assertThat(roomRepository.findById(roomId).getOrFail().toDTO().level.value).isEqualTo(newRoomLevel.value)
    }
}
