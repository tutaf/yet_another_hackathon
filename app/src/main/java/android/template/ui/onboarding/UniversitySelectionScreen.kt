package android.template.ui.onboarding

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
import androidx.compose.ui.text.TextStyle

@Composable
fun UniversitySelectionScreen(viewModel: OnboardingViewModel = hiltViewModel()) {
    val universities = listOf("USM", "UTM", "ASEM")
    val selectedUniversity by viewModel.selectedUniversity.collectAsState()

    Row(horizontalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Select your university", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(96.dp))

            universities.forEach { university ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = university == selectedUniversity,
                        onClick = { viewModel.setUniversity(university) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = university)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
