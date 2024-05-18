package android.template.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DegreeSelectionScreen(viewModel: OnboardingViewModel = hiltViewModel()) {
    val degrees = listOf("Bachelor", "Master", "PhD", "Professor")
    val selectedDegree by viewModel.selectedDegree.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select your Degree")
        Spacer(modifier = Modifier.height(20.dp))

        degrees.forEach { degree ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = degree == selectedDegree,
                    onClick = { viewModel.setDegree(degree) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = degree)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
