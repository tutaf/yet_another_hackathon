package android.template.ui.mymodel



import android.template.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import androidx.compose.material3.Text as Text

@Composable
fun PostScreen(modifier: Modifier = Modifier, viewModel: MyModelViewModel = hiltViewModel()) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
        if (items is MyModelUiState.Success) {
            PostScreen(
                items = (items as MyModelUiState.Success).data,
                onSave = viewModel::addMyModel,
                modifier = modifier
            )

    }
}

@Composable
internal fun PostScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var nameMyModel by remember { mutableStateOf("Compose") }
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = "https://static.wikia.nocookie.net/warrior/images/a/ad/Deathofkevincrow.jpg/revision/latest/scale-to-width-down/1200?cb=20160202110346&path-prefix=ru",
                    contentDescription = "Post Image",
                    modifier = Modifier.width(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = sampleArticles.title, style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,

                    ),
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Name", style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sampleArticles.content, style = TextStyle(
                    fontSize = 20.sp
                ), modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}


// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        PostScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        PostScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}
data class Article(
    val title: String,
    val content: String
)
val sampleArticles = Article(title = "Introduction to Kotlin", content = "Kotlin is a modern, statically typed language used primarily for developing Android apps.")

