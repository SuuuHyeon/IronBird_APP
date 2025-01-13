package com.example.greetingcard.ui.theme.ui.createplan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarView(onDateSelected: (LocalDate) -> Unit) {
    val currentMonth = remember { YearMonth.now() }
    val today = remember { LocalDate.now() }
    val firstDayOfMonth = currentMonth.atDay(1 )
    val lastDayOfMonth = currentMonth.atEndOfMonth()
    val daysOfWeek = java.time.DayOfWeek.values()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        // 월과 년도 표시
        Text(
            text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        // 요일 표시
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            daysOfWeek.forEach { dayIfWeek ->
                Text(
                    text = dayIfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // 날짜 표시
        val dates = (1..lastDayOfMonth.dayOfMonth).map { firstDayOfMonth.plusDays((it-1).toLong()) }
        val groupedDates = dates.chunked(7)

        groupedDates.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                week.forEach { date ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { onDateSelected(date) },
                        // contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            color = if (date == today) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Black
                        )
                    }
                }
            }
        }
    }
}