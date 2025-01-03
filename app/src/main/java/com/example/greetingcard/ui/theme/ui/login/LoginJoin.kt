package com.example.greetingcard.ui.theme.ui.login

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoginJoin(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // finder variable
    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userEmail by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = modifier.background(Color.Red).padding(top = 120.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange =  { userName = it },
            placeholder =  {
                Text(
                    text = "이름을 입력해주세요."
                )
            }
        )
        OutlinedTextField(
            value = userEmail,
            onValueChange = { userEmail = it},
            placeholder = {
                Text(
                    text = "이메일을 입력해주세요."
                )
            }
        )


        Button(onClick = { navController.navigate("login")}) {
            Text("Go to Home");
        }
    }

    Column (


    ) {  }
}