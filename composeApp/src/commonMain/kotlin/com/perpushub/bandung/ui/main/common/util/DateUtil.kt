package com.perpushub.bandung.ui.main.common.util

import com.perpushub.bandung.ui.main.common.model.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

object DateUtil {
    fun formatDate(string: String): String {
        val instant = Instant.parse(string)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.day} ${Month.entries[localDateTime.month.ordinal].label} ${localDateTime.year}"
    }
}