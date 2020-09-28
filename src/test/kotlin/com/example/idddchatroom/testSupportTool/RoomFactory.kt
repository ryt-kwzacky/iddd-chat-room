package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.room.*
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory

object RoomFactory {
    fun genRoom(
        roomId: RoomId = genRoomId(),
        roomName: RoomName = genRoomName(),
        roomLevel: RoomLevel = genRoomLevel(),
        ownerId: RoomOwner
    ): Room = Room.create(
        roomId = roomId,
        roomName = roomName,
        roomLevel = roomLevel,
        ownerId = ownerId
    )
    fun genRoomId(): RoomId = RoomId(RandomIdentityFactory.create())
    fun genRoomName(): RoomName = RoomName(TestDataGenerator.genRandomLengthString(range = 1..16))
    fun genRoomLevel(): RoomLevel = RoomLevel(TestDataGenerator.genRandomInt(0..100))
}
