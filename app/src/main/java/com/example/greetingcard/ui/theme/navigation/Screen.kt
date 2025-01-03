package com.example.greetingcard.ui.theme.navigation

sealed class Screen(val route: String) {
    object Login : Screen(route = "login")
    object LoginFinder: Screen(route = "loginFinder")
    object LoginJoin: Screen(route = "loginJoin")

}

