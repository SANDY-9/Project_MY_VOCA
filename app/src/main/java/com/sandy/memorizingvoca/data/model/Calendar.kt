package com.sandy.memorizingvoca.data.model

data class Calendar(
    val year: Int,
    val month: Int,
    val days: List<List<Date>>,
)