import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CustomAlertDialog(showDialog: Boolean, onDismiss: () -> Unit, navController: NavController) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "환영합니다") },
            text = { Text(text = "메인화면으로 이동하시겠습니까?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss()
                        navController.navigate("home")
                    }) {
                    Text("확인")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }) {
                    Text("취소")
                }
            }
        )
    }
}
