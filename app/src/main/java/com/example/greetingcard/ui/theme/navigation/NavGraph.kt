import com.example.greetingcard.ui.theme.navigation.Screen
import com.example.greetingcard.ui.theme.ui.login.LoginPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greetingcard.ui.theme.ui.login.HomePage

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            LoginPage(navController = navController)
        }
        composable(Screen.Home.route) {
            HomePage()
        }
    }
}
