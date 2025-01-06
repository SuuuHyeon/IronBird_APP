package com.example.greetingcard.ui.theme.ui.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.greetingcard.R
import com.example.greetingcard.data.dto.UserDTO
import com.example.greetingcard.ui.theme.restapi.login.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    // login variable
    val loginViewModel: LoginViewModel = viewModel()

    val isLoading = loginViewModel.isLoading.value;
    val loginResult = loginViewModel.loginResult.value;
    val errorMessage = loginViewModel.errorMessage.value;

    var userInput by remember { mutableStateOf("") }
    var passwordInput by remember{ mutableStateOf("") }
    var shouldShowPwd by remember { mutableStateOf(false) }
    var checkboxStatus by remember {  mutableStateOf(false) }



    val paddingModifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp)

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
    )

    val loginButtonColor =  ButtonDefaults.buttonColors(Color(0xFF87CEEB))


    val pwdResource : (Boolean) -> Int = {
        if(it) { // true
            R.drawable.ic_launcher_background
        }else{
            R.drawable.ic_launcher_background
        }
    }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = {
                Text(
                    text = "이메일 또는 아이디",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            },
            singleLine = true,
            modifier = paddingModifier.padding(top = 30.dp),
            colors = textFieldColors
        )

        OutlinedTextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            placeholder = {
                Text(
                    text = "패스워드",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            },
            maxLines = 1,
            modifier = paddingModifier.padding(top = 12.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = {
                    // 버튼이 눌려지면 비밀번호가 보이도록
                    shouldShowPwd = !shouldShowPwd
                }) {
                    Icon(
                        painter = painterResource(id = pwdResource(shouldShowPwd)),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (shouldShowPwd) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = textFieldColors
        )

        // 자동 로그인 체크박스
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkboxStatus,
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Black,
                    checkedColor = Color(0xFF87CEEB)
                ),
                onCheckedChange = {
                    Log.d("CheckboxActivity", "checkboxContainer: $it")
                    checkboxStatus = it
                }
            )
            Text(
                text = "자동 로그인",
                fontSize = 16.sp
            )
        }

        // 로그인 버튼
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Button(
                onClick = {
                    loginViewModel.loginTest(UserDTO.from(userInput, passwordInput))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = loginButtonColor,
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "로그인",
                    fontSize = 20.sp
                )
            }
        }

        // 하단 링크들
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "회원가입",
                modifier = Modifier.clickable {
                    navController.navigate("loginJoin")
                }
            )
            VerticalDivider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.height(25.dp)
            )
            Text(
                text = "아이디 찾기",
                modifier = Modifier.clickable {
                    navController.navigate("loginFInder/findId")
                })
            VerticalDivider(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.height(25.dp)
            )
            Text(
                text = "비밀번호 재설정",
                modifier = Modifier.clickable {
                    navController.navigate("loginFinder/findPwd")
                })
        }
    }
}