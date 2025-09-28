import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.extensions.percentageColor
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.DateHeader
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun SmallCalendar(
    days: List<Date>,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    month: Int,
    today: Date,
    selectDate: Date?,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            days.forEach { date ->
                val quiz = quizCalendar[date] ?: emptyList()
                FlowColumn (
                    modifier = Modifier.weight(1f),
                ) {
                    DateHeader(
                        date = date,
                        isToday = date == today,
                        otherMonth = month != date.month,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    quiz.forEach {
                        SmallCalendarItem(
                            quiz = it,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(color = Gray30)
    }
}

@Composable
private fun SmallCalendarItem(
    quiz: VocaQuiz,
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
            .background(
                color = quiz.correctPercentage.percentageColor(),
            )
            .clickable(onClick = onItemClick)
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = quiz.quizName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.DarkGray,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
private fun SmallCalendarPreview() {
    MemorizingVocaTheme {
        val date = Date()
        Box(
            modifier = Modifier.fillMaxWidth().height(120.dp)
        ) {
            SmallCalendar(
                days = DateUtils.createCalendar(
                    year = date.year,
                    month = date.month,
                ).days[4],
                month = date.month,
                today = Date(),
                selectDate = null,
                quizCalendar = mapOf(
                    date to listOf(
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 3,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 1,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 10,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(2)
                    ) to listOf(
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 1,
                            totalCount = 20,
                        ),
                    ),
                ),
                onDateSelect = { _, _-> },
            )
        }
    }
}
