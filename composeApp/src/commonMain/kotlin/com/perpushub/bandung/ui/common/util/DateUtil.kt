package com.perpushub.bandung.ui.common.util

import com.perpushub.bandung.ui.common.model.Month
import kotlinx.datetime.LocalDate

object DateUtil {
    fun formatDate(string: String): String {
        val localDate = LocalDate.parse(string)
        return "${localDate.day} ${Month.entries[localDate.month.ordinal].label} ${localDate.year}"
    }
}