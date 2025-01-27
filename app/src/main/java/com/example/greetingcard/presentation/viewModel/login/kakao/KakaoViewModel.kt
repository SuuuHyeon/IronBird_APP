package com.example.greetingcard.presentation.viewModel.login.kakao

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class KakaoViewModel(
    application: Application,
) : AndroidViewModel(application) {

    companion object {
        const val TAG = "KakaoAuthViewModel"
    }

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> get() = _loginResult

    fun handleKakaoLogin(
        navController: NavController
    ) {


        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        loginWithKakao()
                    } else {
                        _loginResult.postValue(Result.failure(error))
                    }
                } else {
                    getUserInfo()
                    navController.navigate("create_plan")
                }
            }
        } else {
            loginWithKakao()
        }
    }

    fun loginWithKakao() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오 로그인 실패", error)
            } else if (token != null) {
                // 로그인 성공 시 액세스 토큰을 확인
                Log.i(TAG, "카카오 로그인 성공 - 액세스 토큰: ${token.accessToken}")
            }
        }

        // 카카오톡 로그인 시도
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            // 카카오 계정 로그인 시도
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun getUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
            }
        }
    }
}

