package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape4

@Composable
internal fun DateHeader(
    date: Date,
    isToday: Boolean,
    otherMonth: Boolean,
    modifier: Modifier = Modifier,
) {
    val backColor = when {
        isToday -> if(otherMonth) Color.LightGray else date.dayOfWeek.color
        else -> Color.Transparent
    }
    val fontColor = when {
        isToday -> Color.White
        else -> if(otherMonth) Color.LightGray else date.dayOfWeek.color
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backColor,
                shape = roundedCornerShape4,
            )
            .padding(vertical = 2.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "${date.day}",
            color = fontColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
        )
    }
}
@Preview(name = "DateHeader")
@Composable
private fun PreviewDateHeader() {
    val date = Date()
    DateHeader(
        date = date,
        isToday = true,
        otherMonth = false,
    )
}