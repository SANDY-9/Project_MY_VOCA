package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.PointerInputChange

internal class FlexibleCalendarState(
    defaultType: CalendarType,
    private val expandFraction: Float,
    private val normalFraction: Float,
    private val smallFraction: Float,
) {

    var type by mutableStateOf(defaultType)
        private set

    var fraction by mutableFloatStateOf(expandFraction)
        private set

    private var scrollState by mutableStateOf(CalendarScroll.NONE)

    private fun updateType() {
        type = when {
            fraction > normalFraction -> CalendarType.EXPANDED_CALENDAR
            fraction > smallFraction -> CalendarType.NORMAL_CALENDAR
            else -> CalendarType.SMALL_CALENDAR
        }
    }

    fun onVerticalDrag(change: PointerInputChange, dragAmount: Float) {
        if(dragAmount > 0 && fraction < expandFraction) {
            fraction += 0.01f
            scrollState = CalendarScroll.DOWN_SCROLL
        }
        if(dragAmount < 0 && fraction > smallFraction) {
            fraction -= 0.01f
            scrollState = CalendarScroll.UP_SCROLL
        }
        if(fraction > 1f) fraction = expandFraction
        updateType()
    }

    fun onDragEnd()  {
        fraction = when {
            fraction > normalFraction -> if(scrollState == CalendarScroll.UP_SCROLL) normalFraction else expandFraction
            fraction > smallFraction -> if(scrollState == CalendarScroll.UP_SCROLL) smallFraction else normalFraction
            else -> smallFraction
        }
        scrollState = CalendarScroll.NONE
        updateType()
    }

}

@Composable
internal fun rememberFlexibleCalendarState(
    defaultType: CalendarType = CalendarType.EXPANDED_CALENDAR,
    expandFraction: Float = 1f,
    normalFraction: Float = 0.5f,
    smallFraction: Float = 0.1f,
): FlexibleCalendarState {
    val state = remember {
        FlexibleCalendarState(
            defaultType = defaultType,
            expandFraction = expandFraction,
            normalFraction = normalFraction,
            smallFraction = smallFraction,
        )
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
