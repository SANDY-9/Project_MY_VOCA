package com.sandy.memorizingvoca.utils

import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

object DateUtils {
    fun getStartOfMonth(year: Int, month: Int): LocalDate {
        val firstDay = getFirstDay(year, month)
        val weekSize = DayOfWeek.entries.size.toLong()
        val startDay = firstDay.minusDays(
            firstDay.dayOfWeek.value % weekSize
        )
        return startDay
    }

    fun getFirstDay(year: Int, month: Int): LocalDate {
        val yearMonth = YearMonth.of(year, month)
        val firstDay = yearMonth.atDay(1)
        return firstDay
    }

    fun getEndOfMonth(year: Int, month: Int): LocalDate {
        val lastDay = getEndDay(year, month)
        val weekSize = DayOfWeek.entries.size.toLong()
        val endDay = lastDay.plusDays(
            DayOfWeek.SATURDAY.dayOfWeek - (lastDay.dayOfWeek.value % weekSize)
        )
        return endDay
    }

    fun getEndDay(year: Int, month: Int): LocalDate {
        val yearMonth = YearMonth.of(year, month)
        val lastDay = yearMonth.atEndOfMonth()
        return lastDay
    }

    fun getWeekIndexOfMonthForLocale(date: LocalDate, locale: Locale = Locale.US): Int {
        val weekFields = WeekFields.of(locale)
        val week = date.get(weekFields.weekOfMonth())
        return week - 1
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
    ): Calendar {
        val days = getMonthCalendarByWeek(year, month)
            .mapIndexed { index, week ->
                week.map { localDate ->
                    Date(
                        localDate = localDate,
                        weekIndex = index,
                    )
                }
            }
        return Calendar(
            year = year,
            month = month,
            days = days,
        )
    }

    // 기본값 : 2025(year)년 9(month)월부터 2026년 12월까지(size=16)
    fun createCalendarList(
        year: Int = 2025,
        month: Int = 9,
        size: Int = 16,
    ): List<Calendar> {
        return (0..< size).map { next ->
            val num = month + next
            val nextMonth = if(num % 12 == 0) 12 else num % 12
            createCalendar(
                year = year + num / 12,
                month = nextMonth,
            )
        }
    }

}

fun main() {
    val a = DateUtils.getWeekIndexOfMonthForLocale(LocalDate.now())
    println(LocalDate.now())
    println(a)
}