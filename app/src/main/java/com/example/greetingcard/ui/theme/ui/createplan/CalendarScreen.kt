package com.example.greetingcard.ui.theme.ui.createplan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.viewModel.createplan.CalendarViewModel
import java.time.LocalDate
import java.time.YearMonth

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = viewModel(),
    onBack: () -> Unit = {} // 뒤로가기 콜백
) {
    val selectedDates by viewModel.selectedDates.collectAsState()
    val currentMonth = remember { LocalDate.now().withDayOfMonth(1) } // 오늘의 첫 날

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "여행일정 등록",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "뒤로가기"
                        )
                    }
                },
                // backgroundColor = MaterialTheme.colorScheme.primary,
                // contentColor = MaterialTheme.colorScheme.onPrimary
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // 선택된 날짜 표시
                Text(
                    text = "시작 날짜 : ${selectedDates.startDate ?: "선택되지 않음"}\n" +
                            "종료 날짜 : ${selectedDates.endDate ?: "선택되지 않음"}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )

                // LazyColumn으로 스크롤 가능한 달력 구현
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(), // 연한 회색 배경
                    contentPadding = PaddingValues(vertical = 16.dp) // 스크롤 뷰 상하 패딩
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
    )
}

fun generateMonths(startMonth: LocalDate, months: Int = 13): List<YearMonth> {
    return (0 until months).map { startMonth.plusMonths(it.toLong()).yearMonth }
}

val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(year, month)