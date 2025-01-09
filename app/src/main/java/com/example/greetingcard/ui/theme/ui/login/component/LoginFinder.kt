package com.example.greetingcard.ui.theme.ui.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.greetingcard.ui.theme.ui.login.view.CustomInputField

@Composable
fun LoginFinder(
    modifier: Modifier = Modifier,
    viewType: String,
    navController: NavController
) {
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newPasswordCheck by remember { mutableStateOf("") }
    var isUpdate by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
//        verticalArrangement = Arrangement.Center
    ) {
        when (viewType) {
            "findId" -> {
                LoginTitle("아이디 찾기")
                FindIdForm(
                    userName = userName,
                    userEmail = userEmail,
                    onValueChange = { field, value ->
                        when (field) {
                            "userName" -> userName = value
                            "userEmail" -> userEmail = value
                        }
                    }
                )
            }
            "findPwd" -> {
                LoginTitle("비밀번호 재설정")
                FindPwdForm(
                    isUpdate = isUpdate,
                    userName = userName,
                    userEmail = userEmail,
                    newPassword = newPassword,
                    newPasswordCheck = newPasswordCheck,
                    onValueChange = { field, value ->
                        when (field) {
                            "userName" -> userName = value
                            "userEmail" -> userEmail = value
                            "newPassword" -> newPassword = value
                            "newPasswordCheck" -> newPasswordCheck = value
                        }
                    }
                )

                Button(
                    onClick = { isUpdate = !isUpdate }
                ) {
                    Text(text = "Toggle Update Form")
                }
            }
        }

        Button(
            onClick = { navController.popBackStack("login", false) }
        ) {
            Text(text = "Go to Home")
        }
    }
}

@Composable
fun FindIdForm(
    userName: String,
    userEmail: String,
    onValueChange: (String, String) -> Unit
) {
    CustomInputField(
        value = userName,
        onValueChange = { onValueChange("userName", it) },
        placeholderText = "아이디",
        leadingIcon = Icons.Default.Person
    )

    CustomInputField(
        value = userEmail,
        onValueChange = { onValueChange("userEmail", it) },
        placeholderText = "이메일을 입력해주세요.",
        leadingIcon = Icons.Default.Email
    )
}

@Composable
fun FindPwdForm(
    isUpdate: Boolean,
    userName: String,
    userEmail: String,
    newPassword: String,
    newPasswordCheck: String,
    onValueChange: (String, String) -> Unit
) {
    FindIdForm(
        userName = userName,
        userEmail = userEmail,
        onValueChange = onValueChange
    )

    if (isUpdate) {
        SetNewPwdForm(
            newPassword = newPassword,
            newPasswordCheck = newPasswordCheck,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SetNewPwdForm(
    newPassword: String,
    newPasswordCheck: String,
    onValueChange: (String, String) -> Unit
) {
    CustomInputField(
        value = newPassword,
        onValueChange = { onValueChange("newPassword", it) },
        placeholderText = "새로운 비밀번호",
        leadingIcon = Icons.Default.Lock
    )

    CustomInputField(
        value = newPasswordCheck,
        onValueChange = { onValueChange("newPasswordCheck", it) },
        placeholderText = "새로운 비밀번호 확인",
        leadingIcon = Icons.Default.Lock
    )
}

