package com.example.greetingcard.presentation.viewModel.home

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.greetingcard.data.repository.post.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostViewModel : ViewModel() {
    private val postDataSource: PostRepository = PostRepository()

    fun createPost(postText: String, selectedImages: List<Uri>) {
        Log.d("========== 게시글 생성 ==========", "클릭")
    }

    // 상태 관리
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _recommendedDestinations =
        listOf("제주도", "부산", "강릉", "경주", "여수", "일본", "몽골", "상하이", "필리핀")
    val recommendedDestinations: List<String> = _recommendedDestinations

    private val _allDestinations = listOf("제주도", "부산", "강릉", "경주", "여수", "서울", "인천", "속초")
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _searchResults.value = emptyList()
        } else {
            _searchResults.value = _allDestinations.filter { it.contains(query) }
        }
    }

    private val _selectedDestination = MutableStateFlow("")
    val selectedDestination = _selectedDestination.asStateFlow()

    fun selectDestination(destination: String) {
        if (_selectedDestination.value == destination && _selectedDestination.value.isNotBlank()) {
            Log.d("여행지 클릭", "선택된 여행지 클릭함")
            _selectedDestination.value = ""
        } else {
            _selectedDestination.value = destination
        }
    }

    // 입력된 쿼리 클리어
    fun onClearQuery() {
        _searchQuery.value = ""
    }


    private val _recentQueries =
        MutableStateFlow<List<String>>(mutableListOf("부산", "여수", "일본", "코타키나발루"))
    val recentQueries: StateFlow<List<String>> = _recentQueries.asStateFlow()

    fun addRecentQuery(query: String) {
        if (query.isNotBlank() && !_recentQueries.value.contains(query)) {
            _recentQueries.update { listOf(query) + it } // 최근 검색어 추가
        }
    }

    fun deleteRecentQuery(query: String) {
        _recentQueries.update { it.filterNot { it == query } }
    }

    fun clearRecentQueries() {
        _recentQueries.value = emptyList()
    }


}
