package android.template.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val selectedUniversity by viewModel.selectedUniversity.collectAsState()
    val selectedDegree by viewModel.selectedDegree.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Main Screen")
        Spacer(modifier = Modifier.height(20.dp))
        selectedUniversity?.let {
            Text(text = "Selected University: $it")
        }
        Spacer(modifier = Modifier.height(20.dp))
        selectedDegree?.let {
            Text(text = "Selected Degree: $it")
        }
    }
}
