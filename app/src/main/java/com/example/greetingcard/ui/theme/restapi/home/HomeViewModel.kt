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

    // 쿼리 변경 메서드(임시 검색)
    fun changeQuery(query: String) {
        searchQuery = query

        filteredPostList = allPostList.filter {
            it.caption.contains(searchQuery) ||
            it.destination.contains(searchQuery)
        }
    }
    // 여행지 선택 메서드
    fun selectDestination(destination: String) {
        selectedDestination = destination
    }

    // 게시물 더미 데이터
    val allPostList = List(20) { i ->
        val captionString = StringBuilder()
        repeat(i + 1) { // i가 0부터 시작하므로 1을 더해줌
            captionString.append("예시 게시물")
        }
        Post(
            if (i % 2 == 0) {
                "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg"
            } else {
                null
            },
            "userNickName${i + 1}",
            "강릉",
            captionString.toString(),
            if (i % 2 == 0) {
                "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg"
            } else {
                null
            }
        )
    }
    var filteredPostList by mutableStateOf(allPostList) // 필터링된 리스트


}


// 더미 데이터 모델
data class Post(
    val userProfileImgUrl: String?, // 유저 프로필 이미지 URL
    val userNickName: String, // 유저 닉네임
    val destination: String, // 여행지
    val caption: String, // 게시물 내용
    val postImgUrl: String?, // 게시물 이미지 URL
//    val postImgList: List<String>? = null // 여행지
)

