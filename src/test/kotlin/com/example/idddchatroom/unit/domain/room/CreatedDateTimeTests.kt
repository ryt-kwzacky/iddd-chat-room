package com.example.idddchatroom.unit.domain.room

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
    val mainDateTime =
        CreatedDateTime(ZonedDateTime.of(
            2016, 1, 1, 2, 30, 0, 0, ZoneId.of("Asia/Tokyo")
        ))
    val twoHourBeforeDateTime =
        CreatedDateTime(ZonedDateTime.of(
            2016, 1, 1, 0, 30, 0, 0, ZoneId.of("Asia/Tokyo")
        ))
    val halfHourBeforeDateTime =
        CreatedDateTime(ZonedDateTime.of(
            2016, 1, 1, 2, 0, 0, 0, ZoneId.of("Asia/Tokyo")
        ))

    @Test
    fun `domain - hasPassedEnoughSince`() {
        // 1. Check duration differences
        assertThat(Duration.between(twoHourBeforeDateTime.toDTO().value, mainDateTime.toDTO().value).toSeconds()).isEqualTo(120 * 60)
        assertThat(Duration.between(halfHourBeforeDateTime.toDTO().value, mainDateTime.toDTO().value).toSeconds()).isEqualTo(30 * 60)

        // 2. Check the method
        assertThat(mainDateTime.hasPassedEnoughSince(twoHourBeforeDateTime)).isTrue()
        assertThat(mainDateTime.hasPassedEnoughSince(halfHourBeforeDateTime)).isFalse()
    }
}