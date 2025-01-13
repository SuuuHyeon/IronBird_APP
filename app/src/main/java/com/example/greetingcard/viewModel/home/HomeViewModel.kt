package com.example.greetingcard.viewModel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Date

class HomeViewModel : ViewModel() {

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
    val myTravelList = listOf(
        "제주",
        "부산",
        "강릉",
        "경주",
        "서울",
        "대구",
        "인천",
        "대전",
        "광주",
        "울산",
        "세종",
        "제주",
        "부산",
        "강릉",
        "경주",
        "서울",
        "대구",
        "인천",
        "대전",
        "광주",
        "울산",
        "세종"
    )

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
            captionString.append("게시물 ${i + 1}번")
        }
        Post(
            i,
            if (i % 2 == 0) {
                "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg"
            } else {
                null
            },
            "userNickName${i + 1}",
            "강릉",
            captionString.toString(),
            if (i % 2 == 0) {
                listOf(
                    "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg",
                    null,
                )
            } else {
                listOf(
                    null,
                    "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg",
                )
            },
            i * 10,
            20,
            Date()
        )
    }
    var filteredPostList by mutableStateOf(allPostList) // 필터링된 리스트

    // 댓글 더미 데이터
    val commentList = List(30) { i ->
        Comment(
            i,
            if (i % 2 == 0) {
                "https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202208/11/e4727123-666e-4603-a2fa-b2478b3130bd.jpg"
            } else {
                null
            },
            "userNickName${i + 1}",
            if (i % 2 == 0) {
                "댓글 ${i + 1}번 내용"
            } else {
                "댓글 ${i + 1}번 텍스트 길이 테스트ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ"
            },
            "2022.08.11"
        )
    }
}


// 더미 데이터 모델

// 게시물
data class Post(
    val postId: Int, // 게시물 ID
    val userProfileImgUrl: String?, // 유저 프로필 이미지 URL
    val userNickName: String, // 유저 닉네임
    val destination: String, // 여행지
    val caption: String, // 게시물 내용
    val postImgUrlList: List<String?>?, // 게시물 이미지 URL
    val likeCount: Int, // 좋아요 수
    val commentCount: Int, // 댓글 수
    val postUploadDate: Date, // 게시물 업로드 날짜
)

// 댓글
data class Comment(
    val postId: Int, // 게시물 ID
    val userProfileImgUrl: String?, // 유저 프로필 이미지 URL
    val userNickName: String, // 유저 닉네임
    val comment: String, // 댓글 내용
    val commentDate: String, // 댓글 작성일
)

