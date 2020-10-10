package com.example.idddchatroom.unit.application.message

import com.example.idddchatroom.core.application.message.CreateReplyMessageCommandHandler
import com.example.idddchatroom.core.application.message.command.CreateReplyMessageCommand
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
class CreateReplyMessageCommandHandlerTests {
    private val userAccountRepository = InMemoryUserAccountRepository()
    private val roomRepository = InMemoryRoomRepository()
    private val messageRepository = InMemoryMessageRepository()
    private val commandHandler = CreateReplyMessageCommandHandler(
        messageRepository
    )

    private val userAccount = UserAccountFactory.genUserAccount()
    private val universalUserId = userAccount.id
    private val room = RoomFactory.genRoom(
        ownerId = RoomOwner(universalUserId)
    )
    private val roomId = room.id
    private val message = MessageFactory.genMessage(
        roomId = roomId,
        sender = MessageSender(universalUserId),
        targetMessageId = null
    )

    private val targetMessageId = message.id
    private val replyMessageSender = MessageSender(universalUserId)
    private val replyMessageText = MessageFactory.genText()
    private val replyMessageImage = MessageFactory.genImage()

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
    fun `handle - create reply message with no AttachedImage`() {
        val command = CreateReplyMessageCommand.create(
            text = replyMessageText.toDTO().value,
            image = null,
            roomId = roomId.value,
            sender = replyMessageSender.toDTO().value.value,
            targetMessageId = targetMessageId.value
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(1)

        /**
         * Perform commandHandler
         */
        val createdReplyMessageId = commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(2)
        assertThat(messageRepository.findById(createdReplyMessageId).exists()).isTrue()
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().toDTO().targetMessageId?.value).isEqualTo(targetMessageId.value)
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().toDTO().image).isNull()
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().isReplyMessage()).isTrue()
    }

    @Test
    fun `handle - create reply message with AttachedImage`() {
        val command = CreateReplyMessageCommand.create(
            text = replyMessageText.toDTO().value,
            image = replyMessageImage.toDTO().path,
            roomId = roomId.value,
            sender = replyMessageSender.toDTO().value.value,
            targetMessageId = targetMessageId.value
        )

        /**
         * Before
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(1)

        /**
         * Perform commandHandler
         */
        val createdReplyMessageId = commandHandler.handle(command)

        /**
         * After
         */
        assertThat(userAccountRepository.count()).isEqualTo(1)
        assertThat(roomRepository.count()).isEqualTo(1)
        assertThat(messageRepository.count()).isEqualTo(2)
        assertThat(messageRepository.findById(createdReplyMessageId).exists()).isTrue()
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().toDTO().targetMessageId?.value).isEqualTo(targetMessageId.value)
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().toDTO().image).isNotEqualTo(null)
        assertThat(messageRepository.findById(createdReplyMessageId).getOrFail().isReplyMessage()).isTrue()
    }
}
