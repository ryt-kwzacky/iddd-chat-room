package com.example.idddchatroom.unit.domain.room

import com.example.idddchatroom.core.domain.message.SentDateTime
import com.example.idddchatroom.core.domain.room.CreatedDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
class CreatedDateTimeTests {
    private final val twoHoursBeforeFromNow: ZonedDateTime = ZonedDateTime.now().minusHours(2)
    private final val thirtyMinutesBeforeFromNow: ZonedDateTime = ZonedDateTime.now().minusMinutes(30)
    val twoHoursBeforeFromNowSentDateTime = SentDateTime(twoHoursBeforeFromNow)
    val thirtyMinutesBeforeFromNowSentDateTime = SentDateTime(thirtyMinutesBeforeFromNow)

    @Test
    fun `domain - hasPassedEnoughToDeleteRoomSince`() {
        // 1. Check duration differences
        assertThat(Duration.between(twoHoursBeforeFromNow, ZonedDateTime.now()).toSeconds()).isEqualTo(60 * 60 * 2)
        assertThat(Duration.between(thirtyMinutesBeforeFromNow, ZonedDateTime.now()).toSeconds()).isEqualTo(30 * 60)

        // 2. Check the method
        assertThat(twoHoursBeforeFromNowSentDateTime.meetsRequirementToDeleteRoom()).isTrue()
        assertThat(thirtyMinutesBeforeFromNowSentDateTime.meetsRequirementToDeleteRoom()).isFalse()
    }
}
