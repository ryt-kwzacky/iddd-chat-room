package com.example.idddchatroom.unit.domain.message

import com.example.idddchatroom.core.domain.message.SentDateTime
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
class SentDateTimeTests {
    private final val tenMinutesBeforeFromNow: ZonedDateTime = ZonedDateTime.now().minusMinutes(10)
    private final val thirtyMinutesBeforeFromNow: ZonedDateTime = ZonedDateTime.now().minusMinutes(30)
    val tenMinutesBeforeFromNowSentDateTime = SentDateTime(tenMinutesBeforeFromNow)
    val thirtyMinutesBeforeFromNowSentDateTime = SentDateTime(thirtyMinutesBeforeFromNow)

    @Test
    fun `domain - isWithinEditableTimeFrom`() {
        // 1. Check duration differences
        assertThat(Duration.between(tenMinutesBeforeFromNow, ZonedDateTime.now()).toSeconds()).isEqualTo(10 * 60)
        assertThat(Duration.between(thirtyMinutesBeforeFromNow, ZonedDateTime.now()).toSeconds()).isEqualTo(30 * 60)

        // 2. Check the method
        assertThat(tenMinutesBeforeFromNowSentDateTime.meetsRequirementToEdit()).isTrue()
        assertThat(thirtyMinutesBeforeFromNowSentDateTime.meetsRequirementToEdit()).isFalse()
    }
}
