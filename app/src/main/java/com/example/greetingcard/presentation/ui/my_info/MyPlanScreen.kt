package com.example.greetingcard.presentation.ui.my_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greetingcard.R
import com.example.greetingcard.data.model.response.MyPlan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlanScreen(navController: NavController) {
    val planList = listOf(
        MyPlan(1, "부산여행 with 친구", "2025-01-20", "2025-01-23", null),
        MyPlan(2, "나홀로 일본", "2025-01-20", "2025-01-23", null),
        MyPlan(3, "프랑스 여행", "2025-01-20", "2025-01-23", null),
        MyPlan(4, "경주 파티", "2025-01-20", "2025-01-23", null),
        MyPlan(5, "경주 파티", "2025-01-20", "2025-01-23", null),
        MyPlan(6, "경주 파티", "2025-01-20", "2025-01-23", null),
        MyPlan(7, "경주 파티", "2025-01-20", "2025-01-23", null),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("내 플랜", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "뒤로가기")
                    }
                },
                actions = {
                    IconButton(onClick = { /* 닫기 로직 */ }) {
                        Icon(Icons.Default.Clear, contentDescription = "닫기")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(planList, key = { it.id }) { plan ->
                PlanCard(plan = plan, onClick = {
                    // 상세 화면으로 이동 로직
                    // navController.navigate("detail/${plan.id}")
                })
            }
        }
    }
}


@Composable
// 복잡한 데이터 아니라 그냥 plan 넘김
fun PlanCard(plan: MyPlan, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() },
    ) {
        Box {
            // 배경 이미지 또는 placeholder
            Image(
                painter = painterResource(id = R.drawable.sea), // 기본 이미지
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            // 반투명 오버레이
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )

            // 내용
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = plan.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 5.dp),
                        text = "${plan.startDate} ~ ${plan.endDate}",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                    IconButton(onClick = { /* 공유 로직 */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "공유",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
