package com.example.greetingcard.presentation.ui.home.planning

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greetingcard.R

// body 부분 그리드 리스트
@Composable
fun FeaturesGrid() {
    val localConfiguration = LocalConfiguration.current
    val cardItemSize = localConfiguration.screenWidthDp.dp / 2
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(max = 600.dp)
//            .wrapContentHeight(),
//        userScrollEnabled = false,
//        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 12.dp)
//    ) {
//        items(features) { (title, description, img) ->
//            FeatureCard(title = title, description = description, img = img)
//        }
//    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        FeatureCard(
            modifier = Modifier.weight(1f),
            width = cardItemSize,
            title = "내 항공권",
            description = "항공권 모음 탭",
            img = R.drawable.ticket_icon,
        )
        FeatureCard(
            modifier = Modifier.weight(1f),
            width = cardItemSize,
            title = "다른거",
            description = "다른거",
            img = R.drawable.ticket_icon,
        )
    }
}

// body 부분 그리드 리스트의 아이템
@Composable
fun FeatureCard(
    modifier: Modifier = Modifier,
    width: Dp,
    title: String,
    description: String,
    img: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffeaeaea),
        ),
        modifier = modifier
            .padding(10.dp)
            .width(width)
            .clip(RoundedCornerShape(20.dp))
            .aspectRatio(1f)
            .clickable { /* Navigate to the feature */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
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
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = "background",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}