package com.example.greetingcard.presentation.ui.home.posting

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.R

@Composable
fun MyTravelList(
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