import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greetingcard.R
import com.example.greetingcard.presentation.ui.home.planning.CustomBottomBar
import com.example.greetingcard.presentation.viewModel.home.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelDestinationScreen(
    navController: NavController,
    postViewModel: PostViewModel,
) {
    val searchQuery by postViewModel.searchQuery.collectAsState()
    val searchResults by postViewModel.searchResults.collectAsState()
    val recommendedDestinations = postViewModel.recommendedDestinations
    val recentQueries by postViewModel.recentQueries.collectAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Text(
                        text = "여행지",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "뒤로가기")
                    }
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                // 검색 바
                SearchBar(
                    query = searchQuery,
                    onQueryChanged = { postViewModel.onSearchQueryChanged(it) },
                    onClearQuery = { postViewModel.onClearQuery() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 최근 검색어 UI
                if (searchQuery.isEmpty()) {
                    RecentSearches(
                        recentQueries = recentQueries,
                        onQueryClick = { postViewModel.onSearchQueryChanged(it) },
                        onDeleteClick = { postViewModel.deleteRecentQuery(it) },
                        onClearAll = { postViewModel.clearRecentQueries() }
                    )
                }

                if (searchQuery.isNotEmpty()) {
                    // 검색 결과
                    if (searchResults.isNotEmpty()) {
                        SearchResultsList(
                            searchResults = searchResults,
                            onDestinationClick = { /* TODO */ }
                        )
                    } else {
                        // 검색 결과 없음 메시지
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "검색 결과가 없습니다",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                } else {
                    // 추천 여행지
                    Text(
                        text = "추천 여행지",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RecommendedDestinations(
                        destinations = recommendedDestinations,
                        onDestinationClick = { /* TODO */ }
                    )
                }
            }
        },
        bottomBar = {
            CustomBottomBar(
                label = "다음",
                enabled = true,
                onBottomBarClick = { /* TODO */ },
            )
        }
    )
}

// 검색바
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = query,
        singleLine = true,
        onValueChange = { onQueryChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp)
    ) {
        TextFieldDefaults.DecorationBox(
            value = query,
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
                if (query.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "clear_text",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onClearQuery()
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

// 검색 결과 리스트
@Composable
fun SearchResultsList(
    searchResults: List<String>,
    onDestinationClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(searchResults, key = { it }) { destination ->
            TravelDestinationItem(destination, onDestinationClick)
        }
    }
}

// 추천 여행지
@Composable
fun RecommendedDestinations(
    destinations: List<String>,
    onDestinationClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(destinations, key = { it }) { destination ->
            TravelDestinationItem(destination, onDestinationClick)
        }
    }
}

@Composable
fun TravelDestinationItem(
    destination: String,
    onClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClick(destination) }

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
            fontSize = 13.sp,
            modifier = Modifier
                .clip(CircleShape)
                .padding(horizontal = 16.dp, vertical = 2.dp),
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun RecentSearches(
    recentQueries: List<String>,
    onQueryClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    onClearAll: () -> Unit
) {
    if (recentQueries.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "최근 검색어",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "전체 삭제",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.clickable { onClearAll() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recentQueries, key = { it }) { query ->
                Box(
                    modifier = Modifier
                        .background(Color(0xffe0e0e0), shape = RoundedCornerShape(50.dp))
                        .wrapContentWidth()
//                        .width(70.dp)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                        .height(30.dp)
                        .clickable { onQueryClick(query) },
                    contentAlignment = Alignment.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(query, fontSize = 14.sp, color = Color(0xff878787))
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            tint = Color(0xffa69d9d),
                            contentDescription = "clear",
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {
                                    onDeleteClick(query)
                                }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
