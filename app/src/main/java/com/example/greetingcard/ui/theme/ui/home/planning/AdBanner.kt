package com.example.greetingcard.ui.theme.ui.home.planning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


// 광고 배너 (임시)
@Composable
fun AdvertisementSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp)
            .height(180.dp)
            .background(Color(0xFFF0975B), shape = RoundedCornerShape(20.dp))
    ) {
        Text(
            text = "300만원 받고 떠나는 휴양 알바\n트리플 X 당근알바",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}
