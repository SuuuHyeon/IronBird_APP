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

    // 드롭다운 메뉴 토글
    fun toggleDropDownMenu() {
        isDropDownExpanded = !isDropDownExpanded
    }



    // 구독한 여행지 목록
    val myTravelList = listOf("제주", "부산", "강릉", "경주", "서울", "대구", "인천", "대전", "광주", "울산", "세종", "제주", "부산", "강릉", "경주", "서울", "대구", "인천", "대전", "광주", "울산", "세종")

    // 선택된 여행지
    var selectedDestination by mutableStateOf<String?>("")

    // 검색 쿼리
    var searchQuery by mutableStateOf("")

    // 쿼리 변경 메서드
    fun changeQuery(query: String) {
        searchQuery = query
    }
    // 여행지 선택 메서드
    fun selectDestination(destination: String) {
        selectedDestination = destination
    }



}