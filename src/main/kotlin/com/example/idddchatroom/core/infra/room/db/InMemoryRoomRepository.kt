package com.example.idddchatroom.core.infra.room.db

import com.example.idddchatroom.core.domain.room.*
import com.example.idddchatroom.dddFoundation.FindResult
import com.example.idddchatroom.sharedKernel.InMemoryBaseRepository
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory

class InMemoryRoomRepository : RoomRepository {
    private val repository = InMemoryBaseRepository<Room>()

    fun reset() {
        repository.reset()
    }

    fun count() = repository.count()

    override fun nextIdentity(): RoomId = RoomId(RandomIdentityFactory.create())

    override fun findById(id: RoomId): FindResult<Room> =
        FindResult(
            repository.findBy { it.toDTO().id == id }
        )

    override fun findAllByRoomOwner(roomOwner: RoomOwner): FindResult<Room> =
        FindResult(
            repository.findBy { it.toDTO().ownerId.value == roomOwner.toDTO().value }
        )

    override fun store(room: Room) {
        val dto = room.toDTO()
        repository.store(
            Room(
                id = dto.id,
                name = RoomName(dto.name.value),
                level = RoomLevel(dto.level.value),
                ownerId = RoomOwner(dto.ownerId.value),
                createdDateTime = CreatedDateTime(dto.createdDateTime.value)
            )
        )
    }

    override fun remove(room: Room) {
        val dto = room.toDTO()
        repository.remove(
            Room(
                id = dto.id,
                name = RoomName(dto.name.value),
                level = RoomLevel(dto.level.value),
                ownerId = RoomOwner(dto.ownerId.value),
                createdDateTime = CreatedDateTime(dto.createdDateTime.value)
            )
        )
    }
}
