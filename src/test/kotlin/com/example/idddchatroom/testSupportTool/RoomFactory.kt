package com.example.idddchatroom.testSupportTool

import com.example.idddchatroom.core.domain.room.*
import com.example.idddchatroom.core.domain.userAccount.UniversalUserId
import com.example.idddchatroom.sharedKernel.RandomIdentityFactory
import com.example.idddchatroom.testSupportTool.TestDataGenerator.genRandomUniversalUserId
import java.time.ZonedDateTime

object RoomFactory {
    fun genRoom(
        roomId: RoomId = genRoomId(),
        ownerId: UniversalUserId = genRandomUniversalUserId(),
        roomName: RoomName = genRoomName(),
        roomLevel: RoomLevel = genRoomLevel(),
        createdDateTime: CreatedDateTime
    ): Room = Room(
        id = roomId,
        ownerId = ownerId,
        name = roomName,
        level = roomLevel,
        createdDateTime = createdDateTime
    )
    fun genRoomId(): RoomId = RoomId(RandomIdentityFactory.create())
    fun genRoomName(): RoomName = RoomName(TestDataGenerator.genRandomLengthString(range = 1..16))
    fun genRoomLevel(): RoomLevel = RoomLevel(TestDataGenerator.genRandomInt(0..100))
    fun genTwoHoursBeforeCreatedDateTime(): CreatedDateTime =
        CreatedDateTime(ZonedDateTime.now().minusHours(2))
}
