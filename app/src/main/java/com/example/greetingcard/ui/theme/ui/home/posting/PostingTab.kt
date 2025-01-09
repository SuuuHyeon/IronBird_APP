package com.example.greetingcard.ui.theme.ui.home.posting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.restapi.home.HomeViewModel

// 더미 데이터 모델
data class Post(
    val userProfileImgUrl: String?, // 유저 프로필 이미지 URL
    val userNickName: String, // 유저 닉네임
    val destination: String, // 여행지
    val caption: String, // 게시물 내용
    val postImgUrl: String?, // 게시물 이미지 URL
//    val postImgList: List<String>? = null // 여행지
)


// 포스팅 탭
@Composable
fun PostingTab(homeViewModel: HomeViewModel, listState: LazyListState) {
    val myTravelList = homeViewModel.myTravelList
    val selectedDestination = homeViewModel.selectedDestination
    val searchQuery = homeViewModel.searchQuery

    // 게시물 더미 데이터
    val posts = List(20) { i ->
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
    }.filter {
        it.caption.contains(searchQuery) || it.destination.contains(searchQuery)
    }


    LazyColumn(
        state = listState,
    ) {
        println("포스팅 탭 listState: ${listState.toString()}")
        item {
            MyTravelList(
                destinations = myTravelList,
                selectedDestination = selectedDestination,
                onDestinationSelected = { homeViewModel.selectDestination(it) },
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        homeViewModel.changeQuery(it)
                    },
                )
            }
        }
        // TODO: 포스팅 필터링 기능 추가
        items(posts) { post ->
            PostItem(
                post = post,
                onClickUserProfile = {},
                onClickLikeIcon = {},
                onClickBubbleIcon = {},
            )
        }
    }
}

// 여행지 검색창
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(45.dp)
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(15.dp))
            .padding(horizontal = 12.dp),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    tint = Color.Gray,
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(22.dp),
                )
                Spacer(modifier = Modifier.width(5.dp))
                if (query.isEmpty()) {
                    Text(
                        text = "원하는 여행지를 검색해보세요!",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Start,
                    )
                }
                innerTextField()
            }
        }
    )
}
