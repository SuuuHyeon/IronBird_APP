package com.example.greetingcard.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen(route = "login")
    object LoginFinder : Screen(route = "loginFinder/{viewType}")
    object LoginJoin : Screen(route = "loginJoin")

    object Splash : Screen(route = "splash_screen")
    object Home : Screen(route = "home")
    object Calender : Screen(route = "plan_calendar")
    object SelectDestination : Screen(route = "plan_destination")
    object CreatePost : Screen(route = "create_post")
}

