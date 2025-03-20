package com.example.greetingcard.presentation.ui.createplan

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.greetingcard.presentation.ui.theme.PurpleGrey40
import com.example.greetingcard.presentation.ui.theme.btnLightBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen(
    navController: NavController,
) {
    var searchQuery = remember { TextFieldValue() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope() // CoroutineScope를 설정

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("어디서?", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                },
//                backgroundColor = Color.White,
//                elevation = 0.dp
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // 설명 텍스트
                Text(
                    text = "원하는 여행지를 검색해보세요!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color(0xFF666666)
                )

                // 여행지 검색
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("여행지 검색") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        containerColor = Color(0xFFF0F0F0)
//                    )
                )

                // 추천 여행지 리스트
                val destinations = listOf(
                    Destination("제주", "100,000원", "https://example.com/jeju.jpg"),
                    Destination("부산", "200,000원", "https://example.com/busan.jpg"),
                    Destination("경주", "150,000원", "https://example.com/gyeongju.jpg")
                )

                destinations.forEach { destination ->
                    DestinationItem(destination = destination, onClick = {
                        scope.launch {
                            Toast.makeText(context, "${destination.name} 선택", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 25.dp)
                    .padding(top = 10.dp, bottom = 25.dp)
                    .fillMaxWidth()
                    .height(50.dp),
//                    enabled = selectedDates.startDate != null && selectedDates.endDate != null,
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xff057bf6),
                        disabledContentColor = PurpleGrey40,
                        disabledContainerColor = btnLightBlue
                    ),

                    onClick = {
                        // TODO : 다음 페이지로 이동
                        navController.navigate("plan_destination")
                    }) {
                    Text(
                        text = "다음 버튼",
                        color = Color.White,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
        }
    )
}

@Composable
fun DestinationItem(
    destination: Destination,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .background(Color(0xFFF0F0F0), RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        // 여행지 이미지
        Image(
            painter = rememberAsyncImagePainter(destination.imageUrl),
            contentDescription = destination.name,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        // 여행지 이름과 가격
        Column(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = destination.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = destination.price,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

data class Destination(
    val name: String,
    val price: String,
    val imageUrl: String
)

@Preview(showBackground = true)
@Composable
fun PreviewDestinationScreen() {
    DestinationScreen(navController = NavController(LocalContext.current))
}
