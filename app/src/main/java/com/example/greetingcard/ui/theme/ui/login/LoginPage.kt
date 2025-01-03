package com.example.greetingcard.ui.theme.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController) {
    Column(
    ) {
        LoginTitle()
        LoginScreen(navController)
        Button(onClick = { navController.navigate("loginFinder")}) {
            Text("Go to Home");
        }
    }
}


