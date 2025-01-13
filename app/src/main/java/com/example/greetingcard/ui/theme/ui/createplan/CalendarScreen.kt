package com.example.greetingcard.ui.theme.ui.createplan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.viewModel.createplan.CalendarViewModel

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = viewModel()) {
    val selectedDates by viewModel.selectedDates.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // 선택된 날짜 표시
        Text(text = "시작 날짜 : ${selectedDates.startDate ?: "선택되지 않음"}\n" +
                    "종료 날짜 : ${selectedDates.endDate ?: "선택되지 않음"}",
            modifier = Modifier.padding(8.dp)
        )
    }

    // 달력
    CalendarView { date -> viewModel.selectDate(date) }
}