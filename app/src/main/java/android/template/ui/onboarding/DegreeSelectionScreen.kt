package android.template.ui.onboarding

import android.template.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontStyle

@Composable
fun DegreeSelectionScreen(viewModel: OnboardingViewModel = hiltViewModel()) {
    val degrees = listOf("Bachelor", "Master", "PhD", "Professor")
    val selectedDegree by viewModel.selectedDegree.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "What's your degree?", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(48.dp))
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


