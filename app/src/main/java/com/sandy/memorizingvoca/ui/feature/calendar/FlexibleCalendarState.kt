package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.PointerInputChange

internal class FlexibleCalendarState {

    var type by mutableStateOf(CalendarType.EXPANDED_CALENDAR)
        private set

    var fraction by mutableFloatStateOf(1f)
        private set

    private var scrollState by mutableStateOf(CalendarScroll.NONE)

    private fun updateType() {
        type = when {
            fraction > 0.5f -> CalendarType.EXPANDED_CALENDAR
            fraction > 0.12f -> CalendarType.NORMAL_CALENDAR
            else -> CalendarType.SMALL_CALENDAR
        }
    }

    fun onVerticalDrag(change: PointerInputChange, dragAmount: Float) {
        if(dragAmount > 0 && fraction < 1f) {
            fraction += 0.01f
            scrollState = CalendarScroll.DOWN_SCROLL
        }
        if(dragAmount < 0 && fraction > 0.12f) {
            fraction -= 0.01f
            scrollState = CalendarScroll.UP_SCROLL
        }
        updateType()
    }

    fun onDragEnd()  {
        fraction = when {
            fraction > 0.5f -> if(scrollState == CalendarScroll.UP_SCROLL) 0.5f else 12f
            fraction > 0.12f -> if(scrollState == CalendarScroll.UP_SCROLL) 0.12f else 0.5f
            else -> 0.12f
        }
        scrollState = CalendarScroll.NONE
        updateType()
    }

}

@Composable
internal fun rememberFlexibleCalendarState(): FlexibleCalendarState {
    val scope = rememberCoroutineScope()
    val state = remember {
        FlexibleCalendarState()
    }
    return state
}


internal enum class CalendarType {
    SMALL_CALENDAR,
    NORMAL_CALENDAR,
    EXPANDED_CALENDAR,
}

internal enum class CalendarScroll {
    UP_SCROLL,
    DOWN_SCROLL,
    NONE,
}
