package com.example.greetingcard.presentation.viewModel.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greetingcard.data.client.login.LoginClient
import com.example.greetingcard.data.model.dto.user.UserDTO
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val isLoading = mutableStateOf(false)
    val loginResult = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)
    var loginResultTest: UserDTO? = null

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

    fun join(userDTO: UserDTO) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                LoginClient.apiService.register(userDTO)
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun loginTest(userDTO: UserDTO) {
        Log.d("userName", userDTO.toString())
        isLoading.value = true
        viewModelScope.launch {
            try {
                // loginTest 호출 (UserDTO를 전달)
                val loginResultTest = LoginClient.apiService.loginTest(userDTO)

                Log.d("LoginTest", "Response: $loginResultTest")

                // 응답이 성공적일 경우
                if (loginResultTest.isSuccessful) {
                    // 성공한 경우 로그인 결과 처리
                    loginResult.value = "Login Success: ${loginResultTest.body()?.toString()}"
                    Log.d("LoginTest", "Login successful: ${loginResultTest.body()}")
                } else {
                    // 실패한 경우 에러 처리
                    errorMessage.value = "Login Failed"
                    Log.e("LoginTest", "Login failed with code: ${loginResultTest.code()}")
                }
            } catch (e: Exception) {
                // 예외 발생 시 처리
                errorMessage.value = "Error: ${e.message}"
                Log.e("LoginTest", "Error occurred", e) // 예외 로그 출력
            } finally {
                // 로딩 상태 종료
                isLoading.value = false
                Log.d("LoginTest", "Loading finished") // 로딩 완료 로그 출력
            }
        }
    }


}
