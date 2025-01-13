package com.example.greetingcard.ui.theme.ui.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.greetingcard.data.model.dto.user.UserDTO
import com.example.greetingcard.ui.theme.ui.login.view.CustomInputField
import com.example.greetingcard.viewModel.login.LoginViewModel


@Composable
fun LoginJoin(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val loginViewModel: LoginViewModel = viewModel()

    Column {
        LoginTitle("회원가입")
        JoinForm(navController, loginViewModel)
    }

}

@Composable
fun JoinForm(
    navController: NavController,
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var userId by remember { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .padding(top = 30.dp)
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomInputField(
                value = userId,
                onValueChange = { userId = it },
                placeholderText = "아이디",
                leadingIcon = Icons.Default.Person
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("비밀번호") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )

            CustomInputField(
                value = userName,
                onValueChange = { userName = it },
                placeholderText = "이름",
                leadingIcon = Icons.Default.Person
            )
            
            CustomInputField(
                value = userEmail,
                onValueChange = { userEmail = it },
                placeholderText = "[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)",
                leadingIcon = Icons.Default.Email
            )
        }
        Button(
            modifier = modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(50.dp), // 버튼 크기 조정
            onClick = {
                // UserDTO 객체 생성
                val userDTO = UserDTO.from(userId, userName, password, userEmail)
                loginViewModel.loginTest(userDTO)
            },
            shape = RoundedCornerShape(12.dp), // 둥근 모서리

        ) {
            Text(
                text = "회원가입",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            modifier = modifier.padding(top = 30.dp),
            onClick = { navController.navigate("login") }) {
            Text("Go to Home")
        }
    }
}

