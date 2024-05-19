package android.template.ui.mymodel


import android.template.data.models.ApiOpportunity
import android.template.ui.post.PostViewModel
import android.template.ui.theme.MyApplicationTheme
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

const val PostScreenArgCategory = "post"
const val PostRouteDefinition = "post/{${PostScreenArgCategory}}"
fun PostScreenRoute(opp: ApiOpportunity) = "post/${opp.id}"


@Composable
fun PostScreen(
    scrollState: ScrollState = rememberScrollState(),
    viewModel: PostViewModel = hiltViewModel(),

    ) {

    val opportunity by viewModel.opportunities.observeAsState(notFound)
    Log.i(">>>this shit", opportunity.toString())

    MyApplicationTheme {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            var nameMyModel by remember { mutableStateOf("Compose") }
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = opportunity.thumbnailLink,
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = opportunity.title, style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,

                        ),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(9.dp))
                OpportunityTags(opportunity)
                Spacer(modifier = Modifier.height(9.dp))
                Text(
                    text = opportunity.content, style = TextStyle(
                        fontSize = 25.sp
                    ), modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 14.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.White),
                                startY = 300f  // Adjust these values based on your container size
                            )
                        )
                )

            }
        }
    }
}

@Composable
fun OpportunityTags(opportunity: ApiOpportunity) {
    val buttonStyle = Modifier
        .padding(horizontal = 8.dp, vertical = 4.dp) // Increased padding for a bigger button
        .height(36.dp) // Optionally set a specific height

    val items = listOf(
        opportunity.degree,
        opportunity.category
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(items) { item ->
            OutlinedButton(onClick = { }, modifier = buttonStyle) {
                Text(text = item, fontSize = 18.sp) // Increased font size
            }
        }
    }
}


//    @Preview(showBackground = true)
//    @Composable
//    private fun DefaultPreview() {
//        MyApplicationTheme {
//            PostScreen(opportunity =  ApiOpportunity())
//        }
//    }
//
//    @Preview(showBackground = true, widthDp = 480)
//    @Composable
//    private fun PortraitPreview() {
//        MyApplicationTheme {
//            PostScreen()
//        }
//    }

val notFound: ApiOpportunity = ApiOpportunity(
    id = -1, // or any value that doesn't correspond to a valid ID
    duration = 0,
    title = "Nothing Found",
    degree = "",
    thumbnailLink = "",
    country = "",
    category = "",
    content = "",
    deadline = "",
    requirements = emptyList()
)