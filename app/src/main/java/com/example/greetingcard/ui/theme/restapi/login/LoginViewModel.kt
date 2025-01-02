package com.example.greetingcard.ui.theme.restapi.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val isLoading = mutableStateOf(false)
    val loginResult = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    fun login(username: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                // Retrofit 클라이언트를 통해 API 호출
                val response = LoginClient.apiService.login(username, password)
                if (response.isSuccessful) {
                    loginResult.value = "Login Success: ${response.body()?.message}"
                } else {
                    errorMessage.value = "Login Failed"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
