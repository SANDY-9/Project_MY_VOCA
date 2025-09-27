package com.sandy.memorizingvoca.utils

import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

object DateUtils {
    fun getStartOfMonth(year: Int, month: Int): LocalDate {
        val yearMonth = YearMonth.of(year, month)
        val firstDay = yearMonth.atDay(1)
        val weekSize = DayOfWeek.entries.size.toLong()
        val startDay = firstDay.minusDays(
            firstDay.dayOfWeek.value % weekSize
        )
        return startDay
    }

    fun getEndOfMonth(year: Int, month: Int): LocalDate {
        val yearMonth = YearMonth.of(year, month)
        val lastDay = yearMonth.atEndOfMonth()
        val weekSize = DayOfWeek.entries.size.toLong()
        val endDay = lastDay.plusDays(
            DayOfWeek.SATURDAY.dayOfWeek - (lastDay.dayOfWeek.value % weekSize)
        )
        return endDay
    }

    fun getWeekOfMonthForLocale(date: LocalDate, locale: Locale = Locale.US): Int {
        val weekFields = WeekFields.of(locale)
        return date.get(weekFields.weekOfMonth())
    }

    fun getMonthCalendar(
        year: Int,
        month: Int,
    ): List<LocalDate> {
        val startDay = getStartOfMonth(year, month)
        val endDay = getEndOfMonth(year, month)
        return generateSequence(startDay) {
            it.plusDays(1)
        }.takeWhile {
            !it.isAfter(endDay)
        }.toList()
    }

    private fun getMonthCalendarByWeek(
        year: Int,
        month: Int,
    ): List<List<LocalDate>> {
        return getMonthCalendar(year, month).chunked(7)
    }

    fun createCalendar(
        year: Int,
        month: Int,
    ): List<List<Date>> = getMonthCalendarByWeek(year, month)
        .mapIndexed { index, week ->
            week.map { localDate ->
                Date(
                    localDate = localDate,
                    week = index
                )
            }
        }

    // 기본값 : 2025(year)년 9(month)월부터 2년(size=24)
    fun createCalendarList(
        year: Int = 2025,
        month: Int = 9,
        size: Int = 24,
    ): List<List<List<Date>>> {
        return (1..size).map { next ->
            val nextMonth = month + next
            createCalendar(
                year = if(nextMonth > 12) year + 1 else year,
                month = if(nextMonth > 12) nextMonth - 12 else nextMonth,
            )
        }
    }

}