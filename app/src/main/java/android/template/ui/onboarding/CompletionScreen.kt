package android.template.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CompletionScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding Complete")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.completeOnboarding()
            navController.navigate("main") {
                popUpTo("onboarding") { inclusive = true }
            }
        }) {
            Text(text = "Get Started")
        }
    }
}
