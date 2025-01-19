package com.example.greetingcard.ui.theme.ui.createplan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.viewModel.createplan.CalendarViewModel
import java.time.LocalDate
import java.time.YearMonth

@Preview(showBackground = true)
@Composable
fun CalendarScreen(viewModel: CalendarViewModel = viewModel()) {
    val selectedDates by viewModel.selectedDates.collectAsState()
    val currentMonth = remember { LocalDate.now().withDayOfMonth(1) } // 오늘의 첫 날

    Column(modifier = Modifier.padding(16.dp)) {
        // 선택된 날짜 표시
        Text(
            text = "시작 날짜 : ${selectedDates.startDate ?: "선택되지 않음"}\n" +
                    "종료 날짜 : ${selectedDates.endDate ?: "선택되지 않음"}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )

        // LazyColumn으로 스크롤 가능한 달력 구현
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp) // 스크롤 뷰의 상하 패딩
        ) {
            // 오늘 포함 13개월 생성
            items(generateMonths(currentMonth, 13)) { month ->
                MonthView(
                    yearMonth = month,
                    today = LocalDate.now(),
                    onDateSelected = { date -> viewModel.selectDate(date) },
                    selectedDates = selectedDates
                )
                Spacer(modifier = Modifier.height(24.dp)) // 각 달 간의 간격
            }
        }
    }
}

fun generateMonths(startMonth: LocalDate, months: Int = 13): List<YearMonth> {
    return (0 until months).map { startMonth.plusMonths(it.toLong()).yearMonth }
}

val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(year, month)