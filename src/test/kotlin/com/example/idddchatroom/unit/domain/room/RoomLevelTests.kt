package com.example.idddchatroom.unit.domain.room

import com.example.idddchatroom.core.domain.room.RoomLevel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class RoomLevelTests {
    val mainRoomLevel = RoomLevel(50)
    val lowerRoomLevel = RoomLevel(0)
    val higherRoomLevel = RoomLevel(100)

    @Test
    fun `domain - isLowerThan`() {
        // 1. Check the method
        assertThat(mainRoomLevel.isLowerThan(lowerRoomLevel)).isFalse()
        assertThat(mainRoomLevel.isLowerThan(higherRoomLevel)).isTrue()
    }
}
