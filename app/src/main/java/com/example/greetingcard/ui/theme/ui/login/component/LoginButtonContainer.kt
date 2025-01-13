package com.example.greetingcard.ui.theme.ui.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.greetingcard.data.model.dto.user.UserDTO
import com.example.greetingcard.viewModel.login.LoginViewModel
import com.example.greetingcard.viewModel.login.kakao.KakaoViewModel


@Composable
fun LoginButtonContainer(
    navController: NavController,
    userInput: String,
    passwordInput: String
) {
    val loginViewModel: LoginViewModel = viewModel()
    val kakaoViewModel: KakaoViewModel = viewModel()

//    val loginButtonColor = ButtonDefaults.buttonColors(Color(0xFF87CEEB))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LoginButton(
            loginViewModel = loginViewModel,
            userInput = userInput,
            passwordInput = passwordInput,
        )

        KakaoButton(
            kakaoViewModel = kakaoViewModel,
            navController = navController
        )

        Test(
            onClick = {
                navController.navigate("home")
            }
        )

    }
}

@Composable
fun Test(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 10.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(Color.Black),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = "홈으로 이동(임시)",
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun LoginButton(
    loginViewModel: LoginViewModel,
    userInput: String,
    passwordInput: String,
) {
    val loginViewModel: LoginViewModel = viewModel()

    Button(
        onClick = {
            loginViewModel.loginTest(UserDTO.from(userId = userInput, password = passwordInput))
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 10.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF87CEEB)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = "로그인",
            fontSize = 20.sp
        )
    }
}


@Composable
fun KakaoButton(
    kakaoViewModel: KakaoViewModel,
    navController: NavController

) {
    kakaoViewModel.loginResult
    Button(
        onClick = {
            kakaoViewModel.handleKakaoLogin(navController = navController)
        }, modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 10.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFEE500)),
        shape = RoundedCornerShape(15.dp)
    )
    {
        Text(
            text = "카카오 로그인 하기",
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}


@Composable
fun NaverButton(
    viewModel: KakaoViewModel,
    navController: NavController
) {
    Column {
        Button(onClick = {
            viewModel.handleKakaoLogin(navController = navController)
        }) {
            Text("카카오 로그인 하기")
        }
        Button(onClick = {}) {
            Text("카카오 로그아웃 하기")
        }
    }
}