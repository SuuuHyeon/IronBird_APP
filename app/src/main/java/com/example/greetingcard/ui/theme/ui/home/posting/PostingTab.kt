package com.example.greetingcard.ui.theme.ui.home.posting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.R
import com.example.greetingcard.ui.theme.restapi.home.HomeViewModel

@Composable
fun PostingTab(homeViewModel: HomeViewModel) {
    val myTravelList = homeViewModel.myTravelList
    val selectedDestination = homeViewModel.selectedDestination

    val posts = remember { // 예시 데이터
        listOf(
            Post(
                "Asd",
                "jaseigc",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                R.drawable.sea
            ),
            Post("asd", "jaseigc", "부산 너무 좋아~~~", R.drawable.sea),
        )
    }

    LazyColumn {
        item {
            TravelDestinationSelector(
                destinations = myTravelList,
                selectedDestination = selectedDestination,
                onDestinationSelected = { homeViewModel.selectDestination(it) },
            )
        }
        item {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                SearchBar(
                    query = "",
                    onQueryChange = { },
                )
            }
        }
        // TODO: 포스팅 필터링 기능 추가
        items(posts) { post ->
            PostItem(post = post, onClick = {})
        }
    }
}


@Composable
fun TravelDestinationSelector(
    destinations: List<String>, // 여행지 이름 리스트
    selectedDestination: String?, // 현재 선택된 여행지
    onDestinationSelected: (String) -> Unit // 선택 이벤트
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(destinations) { destination ->
            val isSelected = destination == selectedDestination
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            width = if (isSelected) 2.dp else 0.dp, // 선택 여부에 따라 테두리 추가
                            color = if (isSelected) Color(0xFF9B84FF) else Color.Transparent, // 선택 시 띠 색상
                            shape = CircleShape
                        )
                        .padding(4.dp) // 테두리와 이미지 간 여백
                        .clickable { onDestinationSelected(destination) }

                ) {
                    Image(
                        painter = painterResource(R.drawable.sea),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "여행지 이미지",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                }
                Text(
                    text = destination,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

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
//                    .height(60.dp)
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


@Composable
fun PostingList(
    posts: List<Post>, // 포스팅 데이터 모델 리스트
    onClickPost: (Post) -> Unit // 포스트 클릭 이벤트
) {

    var searchQuery by remember { mutableStateOf("") }
    val posts = remember { // 예시 데이터
        val filteredPosts = posts.filter {
            it.caption.contains(searchQuery)
        }
        listOf(
            Post(
                "Asd",
                "jaseigc",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                R.drawable.sea
            ),
            Post("asd", "jaseigc", "부산 너무 좋아~~~", R.drawable.sea),
        )
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it }
            )
        }
        items(posts) { post ->
            PostItem(post = post, onClick = { onClickPost(post) })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.sea),
                contentDescription = "유저 프로필 이미지",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = post.userNickName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }
        Image(
            painter = painterResource(post.imageResId),
            contentDescription = "게시물 사진",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f),
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    modifier = Modifier.size(25.dp),
                    contentDescription = "좋아요 아이콘"
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "120", style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ChatBubbleOutline,
                    modifier = Modifier.size(25.dp),
                    contentDescription = "좋아요 아이콘"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "13", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = post.userNickName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = post.caption,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2, // 최대 2줄까지만 표시
                    overflow = TextOverflow.Ellipsis, // 넘칠 경우 "..." 처리
                )
                Text(
                    text = "더 보기",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

    }
}

// 더미 데이터 모델
data class Post(
    val userProfileImgUrl: String,
    val userNickName: String,
    val caption: String,
    val imageResId: Int, // 이미지 리소스 ID
    val postImgList: List<String>? = null // 여행지
)
