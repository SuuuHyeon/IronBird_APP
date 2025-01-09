package com.example.greetingcard.viewModel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    val tabs = listOf("Planning", "Posting")

    var isDropDownExpanded by mutableStateOf(false)

    fun toggleDropDownMenu() {
        isDropDownExpanded = !isDropDownExpanded
    }

    // 현재 선택된 탭 인덱스를 저장
    var selectedTabIndex by mutableIntStateOf(0)

    // 탭 변경 메서드
    fun selectTab(index: Int) {
        selectedTabIndex = index
    }

}