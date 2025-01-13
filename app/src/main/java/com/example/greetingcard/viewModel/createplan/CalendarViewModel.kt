package com.example.greetingcard.viewModel.createplan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class CalendarViewModel : ViewModel() {
    // 시작날짜와 종료날짜 상태
    private val _selectedDates = MutableStateFlow(SelectedDates())
    val selectedDates: StateFlow<SelectedDates> = _selectedDates

    fun selectDate(date: LocalDate) {
        _selectedDates.update { current ->
            if (current.startDate == null) {
                current.copy(startDate = date)  // 첫번째 클릭, 시작 날짜
            } else if (current.endDate == null) {
                current.copy(endDate = date)    // 두번째 클릭, 종료 날짜
            } else {
                SelectedDates(startDate = date) // 다시 누르면 시작 날짜로 초기화
            }
        }
    }
}