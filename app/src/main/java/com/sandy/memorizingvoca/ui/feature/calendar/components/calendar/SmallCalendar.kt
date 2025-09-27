import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.DateHeader
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils

@Composable
internal fun SmallCalendar(
    days: List<Date>,
    month: Int,
    today: Date,
    selectDate: Date?,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        days.forEach { date ->
            Column(
                modifier = Modifier.weight(1f),
            ) {
                DateHeader(
                    date = date,
                    isToday = date == today,
                    otherMonth = month != date.month,
                )
                Spacer(modifier = Modifier.weight(1f))
                HorizontalDivider(color = Gray30)
            }
        }
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
                )[2],
                month = date.month,
                today = Date(),
                selectDate = null,
                onDateSelect = { _, _-> },
            )
        }
    }
}
