package com.sandy.memorizingvoca.ui.feature.calendar

import java.time.LocalDate
import java.time.YearMonth

internal object DateUtils {
    private fun calculateStartOfMonth(yearMonth: YearMonth): LocalDate {
        val firstDay = yearMonth.atDay(1)
        val weekSize = DayOfWeek.entries.size.toLong()
        val startDay = firstDay.minusDays(
            firstDay.dayOfWeek.value % weekSize
        )
        return startDay
    }

    private fun calculateEndOfMonth(yearMonth: YearMonth): LocalDate {
        val lastDay = yearMonth.atEndOfMonth()
        val weekSize = DayOfWeek.entries.size.toLong()
        val endDay = lastDay.plusDays(
            DayOfWeek.SATURDAY.dayOfWeek - (lastDay.dayOfWeek.value % weekSize)
        )
        return endDay
    }

    private fun calculateMonth(
        year: Int,
        month: Int,
    ): List<LocalDate> {
        val yearMonth = YearMonth.of(year, month)
        val startDay = calculateStartOfMonth(yearMonth)
        val endDay = calculateEndOfMonth(yearMonth)
        return generateSequence(startDay) {
            it.plusDays(1)
        }.takeWhile {
            !it.isAfter(endDay)
        }.toList()
    }

    private fun getMonthByWeek(
        year: Int,
        month: Int,
    ): List<List<LocalDate>> {
        return calculateMonth(year, month).chunked(7)
    }

    fun createCalendar(
        year: Int,
        month: Int
    ): List<List<Date>> = getMonthByWeek(year, month)
        .mapIndexed { index, week ->
            week.map { localDate ->
                Date(
                    localDate = localDate,
                    week = index
                )
            }
        }

}