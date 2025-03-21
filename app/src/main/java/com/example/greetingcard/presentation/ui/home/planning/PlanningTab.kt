package com.example.greetingcard.presentation.ui.home.planning

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// 플래닝 화면
@Composable
fun PlanningScreen(listState: LazyListState) {
    println("플래닝탭 listState: $listState")
    LazyColumn(
        state = listState,
    ) {
        item {
            SearchBar()
        }
        item {
            FeaturesGrid()
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            AdvertisementSection()
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

// 검색창
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    var text by remember {
        mutableStateOf("")
    }
    BasicTextField(
        value = text,
        singleLine = true,
        onValueChange = { text = it },
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp, vertical = 8.dp)
            .height(60.dp)
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp),

        ) {
        TextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = it,
            enabled = true,
            singleLine = false,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = { Text("원하는 여행지를 검색해보세요!", fontSize = 14.sp, color = Color.Gray) },
            leadingIcon = {
                Icon(
                    tint = Color.Gray,
                    imageVector = Icons.Default.Search,
                    contentDescription = "search_btn",
                    modifier = Modifier.size(22.dp)
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "clear_text",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                text = ""
                            }
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        )
    }
}
