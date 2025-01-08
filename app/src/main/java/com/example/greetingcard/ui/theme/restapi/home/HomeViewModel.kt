package com.example.greetingcard.ui.theme.restapi.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    val tabs = listOf("Planning", "Posting")
    var isDropDownExpanded by mutableStateOf(false)

    // 현재 선택된 탭 인덱스를 저장
    var selectedTabIndex by mutableIntStateOf(0)

    // 탭 변경 메서드
    fun selectTab(index: Int) {
        selectedTabIndex = index
    }



    // 포스팅
    val myTravelList = listOf("제주", "부산", "강릉", "경주", "서울", "대구", "인천", "대전", "광주", "울산", "세종", "제주", "부산", "강릉", "경주", "서울", "대구", "인천", "대전", "광주", "울산", "세종")
    var selectedDestination by mutableStateOf<String?>("")

    // 여행지 선택 메서드
    fun selectDestination(destination: String) {
        selectedDestination = destination
    }

    fun toggleDropDownMenu() {
        isDropDownExpanded = !isDropDownExpanded
    }


}