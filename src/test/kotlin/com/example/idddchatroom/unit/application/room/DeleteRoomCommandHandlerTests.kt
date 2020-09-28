package com.example.idddchatroom.unit.application.room

import com.example.idddchatroom.core.application.room.DeleteRoomCommandHandler
import com.example.idddchatroom.core.application.room.command.DeleteRoomCommand
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
class DeleteRoomCommandHandlerTests {
    private val roomRepository = InMemoryRoomRepository()
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val messageRepository = InMemoryMessageRepository()
    private val commandHandler = DeleteRoomCommandHandler(
        roomRepository = roomRepository,
        messageRepository = messageRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val room = RoomFactory.genRoom(
        ownerId = RoomOwner(universalUserId)
    )
    private val roomId = room.id

    @Before
    fun setUp() {
        roomRepository.reset()
        userAccountRepository.reset()
        messageRepository.reset()
        userAccountRepository.store(userAccount)
        roomRepository.store(room)
    }

    @Test
    fun `handle - delete Room that has no message`() {
        val command = DeleteRoomCommand.create(
            roomId = roomId.value
        )

        /**
         * Before
         */
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(roomRepository.findById(roomId).exists()).isTrue()

        /**
         * Perform commandHandler
         */
        commandHandler.handle(command)

        /**
         * After
         */
        assertThat(roomRepository.count()).isEqualTo(0)
        assertThat(roomRepository.findById(roomId).exists()).isFalse()
    }

    @Test
    fun `handle - delete Room that has message`() {
        // TODO: Impl after message aggregate
    }
}