import com.example.greetingcard.ui.theme.navigation.Screen
import com.example.greetingcard.ui.theme.ui.login.LoginPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greetingcard.ui.theme.ui.login.LoginFinder
import com.example.greetingcard.ui.theme.ui.login.LoginJoin

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Screen.LoginFinder.route) {
            LoginFinder(navController = navController)
        }
        composable(Screen.LoginJoin.route) {
            LoginJoin(navController = navController)
        }
    }
}
