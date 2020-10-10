package com.example.idddchatroom.unit.application.message

import com.example.idddchatroom.core.application.message.DeleteMessageCommandHandler
import com.example.idddchatroom.core.application.message.DeleteMessageCommand
import com.example.idddchatroom.core.domain.message.MessageSender
import com.example.idddchatroom.core.infra.message.InMemoryMessageRepository
import com.example.idddchatroom.core.infra.room.db.InMemoryRoomRepository
import com.example.idddchatroom.core.infra.userAccount.db.InMemoryUserAccountRepository
import com.example.idddchatroom.testSupportTool.MessageFactory
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
class DeleteMessageCommandHandlerTests {
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val roomRepository = InMemoryRoomRepository()
    private val messageRepository = InMemoryMessageRepository()
    private val commandHandler = DeleteMessageCommandHandler(
        messageRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val room = RoomFactory.genRoom(
        ownerId = universalUserId,
        createdDateTime = RoomFactory.genTwoHoursBeforeCreatedDateTime()
    )
    private val roomId = room.id
    private val message = MessageFactory.genMessage(
        roomId = roomId,
        sender = MessageSender(universalUserId),
        targetMessageId = null
    )

    private val targetMessageId = message.id

    @Before
    fun setUp() {
        userAccountRepository.reset()
        userAccountRepository.store(userAccount)
        roomRepository.reset()
        roomRepository.store(room)
        messageRepository.reset()
        messageRepository.store(message)
    }

    @Test
    fun `handle - edit message`() {
        val command = DeleteMessageCommand.create(
            messageId = targetMessageId.value
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(1)
        assertThat(messageRepository.findById(targetMessageId).exists()).isTrue()

        /**
         * Perform commandHandler
         */
        commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(0)
        assertThat(messageRepository.findById(targetMessageId).exists()).isFalse()
    }
}

