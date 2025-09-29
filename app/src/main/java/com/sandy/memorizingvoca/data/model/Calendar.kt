package com.sandy.memorizingvoca.data.model

data class Calendar(
    val year: Int,
    val month: Int,
    val days: List<List<Date>>,
) {
    val otherMonth: Map<Date, Boolean> = days.flatMap {
        it.map { date -> date to (date.month != month) }
    }.toMap()
}