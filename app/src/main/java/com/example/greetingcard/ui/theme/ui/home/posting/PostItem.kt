package com.example.greetingcard.ui.theme.ui.home.posting

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.greetingcard.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostItem(
    post: Post,
    onClickUserProfile: () -> Unit,
    onClickLikeIcon: () -> Unit,
    onClickBubbleIcon: () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // 유저 프로필 섹션
        ProfileSection(post.userNickName, post.userProfileImgUrl, onClickUserProfile)
        // 이미지 섹션
        PostImgSection(post.postImgUrl, onClickPostImg = { Log.d("PostItem", "게시물 이미지 클릭") })
        Spacer(modifier = Modifier.height(4.dp))

        // 좋아요, 댓글 섹션
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
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "13", style = MaterialTheme.typography.bodyLarge)
            }
        }

        // 캡션 섹션
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
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis, // 넘칠 경우 "..." 처리
                )
                Text(
                    text = if (isExpanded) "접기" else "더보기",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.clickable { isExpanded = !isExpanded }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

    }
}

// 유저 프로필 섹션
@Composable
fun ProfileSection(
    userNickName: String,
    userProfileImgUrl: String?,
    onClickUserProfile: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { onClickUserProfile() }
    ) {
        AsyncImage(
            model = userProfileImgUrl,
            error = painterResource(R.drawable.user_profile),
            contentDescription = "유저 프로필 이미지",
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = userNickName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

// 게시물 이미지 섹션
@Composable
fun PostImgSection(postImgUrl: String?, onClickPostImg: () -> Unit) {
    var isError by remember { mutableStateOf(false) }


    if (isError) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1.2f)
                .background(Color.LightGray)
                .clickable { onClickPostImg() },
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(R.drawable.refresh_icon),
                contentDescription = "로딩 실패 아이콘",
            )
        }
    } else {
        AsyncImage(
            model = postImgUrl,
            onError = { isError = true },
            contentDescription = "게시물 사진",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.2f),
            contentScale = ContentScale.FillBounds
        )
    }
}
