package com.example.greetingcard

import SetUpNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.ui.theme.GreetingCardTheme
import com.example.greetingcard.ui.theme.navigation.Screen
import com.example.greetingcard.ui.theme.ui.login.LoginFinder
import com.example.greetingcard.ui.theme.ui.login.LoginJoin
import com.example.greetingcard.ui.theme.ui.login.LoginPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                val navController =rememberNavController()
                SetUpNavGraph(navController = navController)
            }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Column(
            modifier = Modifier.fillMaxWidth()

        ) {
            val navController = rememberNavController()
            LoginJoin(navController = navController)
        }
    }
}


