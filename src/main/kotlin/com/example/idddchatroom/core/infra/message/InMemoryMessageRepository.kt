package com.example.idddchatroom.core.infra.message

import com.example.idddchatroom.core.domain.message.*
import com.example.idddchatroom.core.domain.room.RoomId
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.dddFoundation.FindAllResult
import com.example.idddchatroom.dddFoundation.FindResult
import com.example.idddchatroom.sharedKernel.InMemoryBaseRepository
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory

class InMemoryMessageRepository : MessageRepository {
    private val repository = InMemoryBaseRepository<Message>()

    fun reset() {
        repository.reset()
    }

    fun count() = repository.count()

    override fun nextIdentity(): MessageId = MessageId(RandomIdentityFactory.create())

    override fun findById(id: MessageId): FindResult<Message> =
        FindResult(repository.findBy { it.toDTO().id == id })

    override fun findAllByUniversalUserId(universalUserId: UniversalUserId): FindAllResult<Message> =
        FindAllResult(
            repository.findAllBy { it.toDTO().sender.value == universalUserId }
        )

    override fun findAllByRoomId(roomId: RoomId): FindAllResult<Message> =
        FindAllResult(
            repository.findAllBy { it.toDTO().roomId == roomId }
        )

    override fun store(message: Message) {
        val dto = message.toDTO()
        repository.store(
            Message(
                id = dto.id,
                text = MessageText(dto.text.value),
                image = dto.image?.let { AttachedImage(it.path) },
                roomId = dto.roomId,
                sender = MessageSender(dto.sender.value),
                sentDateTime = SentDateTime(dto.sentDateTime.value),
                targetMessageId = dto.targetMessageId
            )
        )
    }

    override fun remove(message: Message) {
        val dto = message.toDTO()
        repository.remove(
            Message(
                id = dto.id,
                text = MessageText(dto.text.value),
                image = dto.image?.let { AttachedImage(it.path) },
                roomId = dto.roomId,
                sender = MessageSender(dto.sender.value),
                sentDateTime = SentDateTime(dto.sentDateTime.value),
                targetMessageId = dto.targetMessageId
            )
        )
    }
}
