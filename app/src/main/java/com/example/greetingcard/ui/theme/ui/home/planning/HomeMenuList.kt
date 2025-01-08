package com.example.greetingcard.ui.theme.ui.home.planning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.greetingcard.R

// body 부분 그리드 리스트
@Composable
fun FeaturesGrid() {
    val features = listOf(
        Triple("일정 만들기", "주변의 핫 플레이스들을 지도로 확인하세요!", R.drawable.map_img),
        Triple("일정 추천받기", "취향에 맞게 일정을 추천해드려요!", R.drawable.map_img),
        Triple("바우처", "나라별, 지역별 할인 정보를 확인할 수 있어요!", R.drawable.map_img),
        Triple("내 일정 리스트", "나라별, 지역별 여행의 날짜를 알 수 있어요!", R.drawable.map_img)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp)
            .wrapContentHeight(),
        userScrollEnabled = false,
        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 12.dp)
    ) {
        items(features) { (title, description, img) ->
            FeatureCard(title = title, description = description, img = img)
        }
    }
}

// body 부분 그리드 리스트의 아이템
@Composable
fun FeatureCard(title: String, description: String, img: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
//            contentColor = Color.Black
        ),
//        border = BorderStroke(width = 0.dp, brush = Brush.linearGradient(listOf(Color.Unspecified, Color.Unspecified))),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp))
            .aspectRatio(1f)
//            .padding(4.dp)
//            .shadow(2.dp, shape = RoundedCornerShape(20.dp))
            .clickable { /* Navigate to the feature */ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "메인 화면 이미지",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = description,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}