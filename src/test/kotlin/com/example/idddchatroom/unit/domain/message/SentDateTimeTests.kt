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
    val mainDateTime =
        SentDateTime(ZonedDateTime.of(
            2016, 1, 1, 2, 30, 0, 0, ZoneId.of("Asia/Tokyo")
        ))
    val tenMinutesBeforeDateTime =
        SentDateTime(ZonedDateTime.of(
            2016, 1, 1, 2, 20, 0, 0, ZoneId.of("Asia/Tokyo")
        ))
    val thirtyMinutesBeforeDateTime =
        SentDateTime(ZonedDateTime.of(
            2016, 1, 1, 2, 0, 0, 0, ZoneId.of("Asia/Tokyo")
        ))

    @Test
    fun `domain - isWithinEditableTimeFrom`() {
        // 1. Check duration differences
        assertThat(Duration.between(tenMinutesBeforeDateTime.toDTO().value, mainDateTime.toDTO().value).toSeconds()).isEqualTo(10 * 60)
        assertThat(Duration.between(thirtyMinutesBeforeDateTime.toDTO().value, mainDateTime.toDTO().value).toSeconds()).isEqualTo(30 * 60)

        // 2. Check the method
        assertThat(mainDateTime.isWithinEditableTimeFrom(tenMinutesBeforeDateTime)).isTrue()
        assertThat(mainDateTime.isWithinEditableTimeFrom(thirtyMinutesBeforeDateTime)).isFalse()
    }
}
