package com.example.greetingcard.presentation.ui.createplan

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.greetingcard.presentation.ui.theme.PurpleGrey40
import com.example.greetingcard.presentation.ui.theme.btnLightBlue
import com.example.greetingcard.presentation.viewModel.createplan.CalendarViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = viewModel(),
    onBack: () -> Unit = {} // 뒤로가기 콜백
) {
    val selectedDates by viewModel.selectedDates.collectAsState()
    val currentMonth = remember { LocalDate.now().withDayOfMonth(1) }
    val travelDurationText = if (selectedDates.startDate != null && selectedDates.endDate != null) {
        val days = ChronoUnit.DAYS.between(selectedDates.startDate, selectedDates.endDate)
        "${days}박 ${days + 1}일 일정 등록하기"
    } else {
        "날짜를 선택해주세요"
    }

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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = "뒤로가기"
                        )
                    }
                },
            )
        },
        content = { innerPadding ->
            Log.d("innerPadding", "$innerPadding")
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                // 스크롤 가능한 달력
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // 오늘 포함 13개월 생성
                    items(generateMonths(currentMonth, 13)) { month ->
                        MonthView(
                            yearMonth = month,
                            today = LocalDate.now(),
                            onDateSelected = { date -> viewModel.selectDate(date) },
                            selectedDates = selectedDates
                        )
                        Spacer(modifier = Modifier.height(20.dp)) // 각 달 간의 간격
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 25.dp)
                    .padding(top = 10.dp, bottom = 25.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                    enabled = selectedDates.startDate != null && selectedDates.endDate != null,
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xff057bf6),
                        disabledContentColor = PurpleGrey40,
                        disabledContainerColor = btnLightBlue
                    ),

                    onClick = {
                        // TODO : 다음 페이지로 이동
                        navController.navigate("plan_destination")
                    }) {
                    Text(
                        text = travelDurationText, color = Color.White, style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        },
    )
}

fun generateMonths(startMonth: LocalDate, months: Int = 13): List<YearMonth> {
    return (0 until months).map { startMonth.plusMonths(it.toLong()).yearMonth }
}

val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(year, month)