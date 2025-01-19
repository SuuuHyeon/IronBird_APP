package com.example.greetingcard.ui.theme.ui.createplan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.greetingcard.ui.theme.Purple40
import com.example.greetingcard.ui.theme.Purple80 // ../Color.kt에서 정의된 Purple80 변수
import com.example.greetingcard.viewModel.createplan.SelectedDates
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MonthView(
    yearMonth: YearMonth,
    today: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    selectedDates: SelectedDates
) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfWeek = yearMonth.atDay(1).dayOfWeek.value % 7 // 일요일 시작 기준
    val days = (1..daysInMonth).map { yearMonth.atDay(it) }

    Column(modifier = Modifier.padding(8.dp)) {
        // 연도 및 월 표시
        Text(
            text = "${yearMonth.year}.${yearMonth.monthValue.toString().padStart(2, '0')}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        // 요일 헤더 표시 (일 ~ 토)
        Row {
            listOf("일", "월", "화", "수", "목", "금", "토").forEachIndexed { index, day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = when (index) {
                        0 -> Color.Red // 일요일 빨간색
                        6 -> Color.Blue // 토요일 파란색
                        else -> MaterialTheme.colorScheme.onSurface
                    },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // 날짜 렌더링
        val rows = (days.size + firstDayOfWeek - 1) / 7 + 1
        for (row in 0 until rows) {
            Spacer(modifier = Modifier.height(4.dp)) // 각 주 간격
            Row {
                for (col in 0..6) {
                    val dayIndex = row * 7 + col - firstDayOfWeek
                    val date = days.getOrNull(dayIndex)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            // .padding(4.dp)
                            // .border(1.dp, Color.Gray) // 랜더링 확인 용 테두리
                            .aspectRatio(1f) // 정사각형 셀
                            .clickable(enabled = date != null) { date?.let(onDateSelected) },
                        contentAlignment = Alignment.TopCenter // 상단 정렬
                    ) {
                        if (date != null) {
                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // 날짜 숫자 부분을 wrapping하는 Box 추가
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = when (date) {
                                                selectedDates.startDate, selectedDates.endDate -> Purple80
                                                else -> Color.Transparent // 선택되지 않은 날짜는 투명
                                            },
                                            shape = RoundedCornerShape(8.dp) // 숫자만 감싸는 둥근 사각형
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp), // 내부 패딩 추가
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = date.dayOfMonth.toString(),
                                        color = when {
                                            date == today -> Purple40 // 오늘 날짜 글씨 흰색
                                            date.dayOfWeek == DayOfWeek.SUNDAY -> Color.Red // 일요일 빨간색
                                            date.dayOfWeek == DayOfWeek.SATURDAY -> Color.Blue // 토요일 파란색
                                            date in listOf(selectedDates.startDate, selectedDates.endDate) -> Color.White
                                            else -> MaterialTheme.colorScheme.onSurface
                                        },
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                // "오늘" 표시
                                if (date == today) {
                                    // Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "오늘",
                                        color = Purple40, // 오늘 텍스트 강조 색상
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}