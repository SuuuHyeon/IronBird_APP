import com.example.greetingcard.ui.theme.navigation.Screen
import com.example.greetingcard.ui.theme.ui.login.LoginPage
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greetingcard.ui.theme.restapi.home.HomeViewModel
import com.example.greetingcard.ui.theme.ui.home.HomePage
import com.example.greetingcard.ui.theme.ui.home.planning.CreatePlanPage

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
            val homeViewModel: HomeViewModel = viewModel()
            HomePage(navController = navController, homeViewModel = homeViewModel)
        }
        composable(Screen.CreatePlan.route) {
            CreatePlanPage()
        }
    }
}
