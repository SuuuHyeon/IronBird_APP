package com.example.greetingcard.presentation.ui.common.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ArtistCard() {
    Text(
        text = "레이아웃을 잡아주지 않으면",
        modifier = Modifier.background(Color.White)
    )
    Text("겹치게 됩니다.")
}


@Preview
@Composable
fun ColumnCard() {
    Column(modifier = Modifier.background(Color.White)) {
        Text("Column은 수직정렬")
        Text("수직정렬완료")
    }
}

@Preview
@Composable
fun RowCard() {
    Row(
        modifier = Modifier.background(Color.White)
    ) {
        Text("Row는 수평정렬")
        Text("수평정렬")
    }
}

@Preview
@Composable
fun BoxCard() {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Text("첫 번째 텍스트", modifier = Modifier.align(Alignment.TopStart))
        Text("두 번째 텍스트", modifier = Modifier.align(Alignment.BottomEnd))
    }

}