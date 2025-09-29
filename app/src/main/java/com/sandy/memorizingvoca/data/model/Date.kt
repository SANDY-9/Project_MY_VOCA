package com.sandy.memorizingvoca.data.model

import androidx.compose.ui.graphics.Color
import com.sandy.memorizingvoca.ui.theme.DarkBlue
import com.sandy.memorizingvoca.ui.theme.DarkRed
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate


data class Date(
    val localDate: LocalDate = LocalDate.now(),
) {
    val weekIndex: Int = DateUtils.getWeekIndexOfMonthForLocale(localDate)
    val year: Int = localDate.year
    val month: Int = localDate.monthValue
    val day: Int = localDate.dayOfMonth
    val dayOfWeek: DayOfWeek = DayOfWeek.get(localDate.dayOfWeek.value)
}

enum class DayOfWeek(
    val dayOfWeek: Int = 0,
    val desc: String = "",
    val fullName: String = "",
    val color: Color = Color.DarkGray,
) {
    SUNDAY(
        desc = "일",
        fullName = "일요일",
        dayOfWeek = 0,
        color = DarkRed,
    ),
    MONDAY(
        desc = "월",
        fullName = "월요일",
        dayOfWeek = 1,
    ),
    TUESDAY(
        desc = "화",
        fullName = "화요일",
        dayOfWeek = 2,
    ),
    WEDNESDAY(
        desc = "수",
        fullName = "수요일",
        dayOfWeek = 3,
    ),
    THURSDAY(
        desc = "월",
        fullName = "목요일",
        dayOfWeek = 4,
    ),
    FRIDAY(
        desc = "금",
        fullName = "금요일",
        dayOfWeek = 5,
    ),
    SATURDAY(
        desc = "토",
        fullName = "토요일",
        dayOfWeek = 6,
        color = DarkBlue
    );

    companion object {
        fun get(dayOfWeek: Int): DayOfWeek {
            return DayOfWeek.entries.find { it.dayOfWeek == dayOfWeek } ?: SUNDAY
        }

        fun list(): List<DayOfWeek> {
            return listOf(
                SUNDAY,
                MONDAY,
                TUESDAY,
                WEDNESDAY,
                THURSDAY,
                FRIDAY,
                SATURDAY,
            )
        }
    }
}