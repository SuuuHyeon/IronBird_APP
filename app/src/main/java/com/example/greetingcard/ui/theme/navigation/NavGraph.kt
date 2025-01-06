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
        composable(Screen.LoginFinder.route) { backStackEntry ->
            val viewType = backStackEntry.arguments?.getString("viewType") ?: "findId"
            LoginFinder(navController = navController, viewType = viewType)
        }
        composable(Screen.LoginJoin.route) {
            LoginJoin(navController = navController)
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
