package com.example.idddchatroom.unit.application.message

import com.example.idddchatroom.core.application.message.CreateMessageCommandHandler
import com.example.idddchatroom.core.application.message.command.CreateMessageCommand
import com.example.idddchatroom.core.domain.message.MessageSender
import com.example.idddchatroom.core.domain.room.RoomOwner
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
class CreateMessageCommandHandlerTests {
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val roomRepository = InMemoryRoomRepository()
    private val messageRepository = InMemoryMessageRepository()
    private val commandHandler = CreateMessageCommandHandler(
        messageRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val room = RoomFactory.genRoom(
        ownerId = RoomOwner(universalUserId)
    )
    private val roomId = room.id
    private val text = MessageFactory.genText()
    private val image = MessageFactory.genImage()
    private val sender = MessageSender(universalUserId)

    @Before
    fun setUp() {
        userAccountRepository.reset()
        userAccountRepository.store(userAccount)
        roomRepository.reset()
        roomRepository.store(room)
        messageRepository.reset()
    }

    @Test
    fun `handle - create Message with no AttachedImage`() {
        val command = CreateMessageCommand.create(
            text = text.toDTO().value,
            image = null,
            roomId = roomId.value,
            sender = sender.toDTO().value.value
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(0)

        /**
         * Perform commandHandler
         */
        val createdMessageId = commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(1)
        assertThat(messageRepository.findById(createdMessageId).exists()).isTrue()
        assertThat(messageRepository.findById(createdMessageId).getOrFail().toDTO().targetMessageId).isNull()
        assertThat(messageRepository.findById(createdMessageId).getOrFail().toDTO().image).isNull()
        assertThat(messageRepository.findById(createdMessageId).getOrFail().isReplyMessage()).isFalse()
    }

    @Test
    fun `handle - create Message with AttachedImage`() {
        val command = CreateMessageCommand.create(
            text = text.toDTO().value,
            image = image.toDTO().path,
            roomId = roomId.value,
            sender = sender.toDTO().value.value
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(0)

        /**
         * Perform commandHandler
         */
        val createdMessageId = commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(1)
        assertThat(messageRepository.findById(createdMessageId).exists()).isTrue()
        assertThat(messageRepository.findById(createdMessageId).getOrFail().toDTO().targetMessageId).isNull()
        assertThat(messageRepository.findById(createdMessageId).getOrFail().toDTO().image).isNotEqualTo(null)
        assertThat(messageRepository.findById(createdMessageId).getOrFail().isReplyMessage()).isFalse()
    }
}
