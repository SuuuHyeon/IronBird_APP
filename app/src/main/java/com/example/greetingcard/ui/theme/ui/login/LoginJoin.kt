package com.example.greetingcard.ui.theme.ui.login

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun LoginJoin(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userEmail by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    Column {
        LoginTitle("회원가입")
        JoinForm(navController)
    }

}

@Composable
fun JoinForm(
    navController: NavController,
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
        var passwordCheck by remember { mutableStateOf(TextFieldValue()) }

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

        CustomInputField(
            value = password,
            onValueChange = { password = it },
            placeholderText = "비밀번호",
            leadingIcon = Icons.Default.Lock,
            trailingIcon = Icons.Default.Check
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
            modifier = modifier.padding(top = 30.dp),
            onClick = { navController.navigate("login") }) {
            Text("Go to Home");
        }

    }
}

