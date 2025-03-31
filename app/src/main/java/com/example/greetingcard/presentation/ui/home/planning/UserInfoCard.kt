package com.example.greetingcard.presentation.ui.home.planning

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.greetingcard.R

@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    onUserProfileClicked: () -> Unit,
    onPlanClicked: () -> Unit,
    onPostingClicked: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f)
    ) {
        Box {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onUserProfileClicked() }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        // TODO: 유저 프로필 이미지 url 주입
                        model = "",
                        error = painterResource(R.drawable.user_profile),
                        contentDescription = "user_info_profile_image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "김수현",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "초보 여행자",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 0.2.dp,
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clickable { onPlanClicked() }
                    ) {
//                        Icon(
//                            imageVector = Icons.Default.,
//                            contentDescription = "여행계획",
//                            modifier = Modifier.size(40.dp)
//                        )
                        Image(
                            painter = painterResource(R.drawable.airplane_icon),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp),
                            contentDescription = "travel_icon"
                        )
                        Text("Plan", fontSize = 14.sp)
                    }
                    VerticalDivider(
                        color = Color.Gray,
                        thickness = 0.2.dp,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clickable { onPostingClicked() }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.send_icon),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(40.dp),
                            contentDescription = "posting_icon"
                        )
                        Text("Post", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}