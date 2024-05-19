
package android.template.ui.feed

import android.app.Activity
import android.content.Context
import android.template.R
import android.template.data.erasmulApi.models.ApiOpportunity
import android.template.ui.theme.MyApplicationTheme
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel


//import coil.compose.AsyncImage



const val FeedScreenRouteDefinition = "feed"


@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
    onClick: (ApiOpportunity) -> Unit

) {
    val opportunities by viewModel.opportunitiesUiState.collectAsStateWithLifecycle()
    FeedScreen(
        opportunities = opportunities,
        onClick = onClick,
        modifier = modifier
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(
    opportunities: List<ApiOpportunity>,
    onClick: (ApiOpportunity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        CenterAlignedTopAppBar(
            title = {
                SearchBar(
                    onSearch = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(opportunities, key = { it.id }) { opportunity ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .clickable { onClick(opportunity) }

                ) {
                    Element(opp = opportunity)
                }
            }
        }
    }

}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color.LightGray,
            modifier = Modifier.weight(5f),
            shape = RoundedCornerShape(16.dp),

        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    onSearch(it)
                },
                placeholder = { Text(text = "Search...") },
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(color = Color.Transparent)
                    .weight(4f),
            )
            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { text = "" },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {
                Icon(Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .clickable {
                            focusManager.clearFocus()
                            context.hideKeyboard()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))


        Surface(
            color = Color.LightGray,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp)

        ){
            IconButton(
                onClick = { text = "" },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter= painterResource(id = R.drawable.filter),
                    contentDescription = null

                )
            }
        }


    }
}

@Composable
fun Element(opp: ApiOpportunity){
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
//            AsyncImage(
//                model = "https://static.wikia.nocookie.net/warrior/images/a/ad/Deathofkevincrow.jpg/revision/latest/scale-to-width-down/1200?cb=20160202110346&path-prefix=ru",
//                contentDescription = "Post Image",
//                modifier = Modifier.width(200.dp)
//            )
        }

        Text(
            text = opp.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = opp.content)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OpportunityTags(opp = opp)

            Spacer(modifier = Modifier.width(8.dp)) // Add space between the tags and details

            OpportunityDetails(opp = opp)
        }

    }
}


@Composable
fun OpportunityTags(opp: ApiOpportunity) {
    val buttonStyle = Modifier.padding(horizontal = 2.dp) // Adjust padding for button

    val items = listOf(
        opp.degree,
        opp.category
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            OutlinedButton(onClick = { }, modifier = buttonStyle) {
                Text(
                    text = item,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun OpportunityDetails(opp: ApiOpportunity) {
    val textStyle = TextStyle(color = Color.Gray) // Define a common text style

    val details = listOf(
        "${opp.duration} days",
        "|",
        opp.deadline
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add space of 8.dp between elements
    ) {
        items(details) { detail ->
            Text(
                text = detail,
                style = textStyle // Apply the text style
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        FeedScreen(sampleOpportunities, onClick = {})
    }
}

fun Context.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = (this as? Activity)?.currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


val sampleOpportunities = listOf(
    ApiOpportunity(
        id = 1,
        duration = 60,
        title = "Internship in Software Development",
        degree = "Bachelor's",
        thumbnailLink = "https://example.com/thumbnail1.jpg",
        country = "USA",
        category = "Internship",
        content = "Join us for a 3-month internship in software development. Work with a team of experienced developers.",
        deadline = "2024-06-30"
    ),
    ApiOpportunity(
        id = 2,
        duration = 90,
        title = "Research Assistant Position",
        degree = "Master's",
        thumbnailLink = "https://example.com/thumbnail2.jpg",
        country = "Germany",
        category = "Research",
        content = "Assist in cutting-edge research in AI and Machine Learning. This position is open for Master's students.",
        deadline = "2024-07-15"
    ),
    ApiOpportunity(
        id = 3,
        duration = 45,
        title = "Business Analyst Internship",
        degree = "Bachelor's",
        thumbnailLink = "https://example.com/thumbnail3.jpg",
        country = "UK",
        category = "Business",
        content = "Gain hands-on experience as a business analyst. This internship is perfect for business students.",
        deadline = "2024-08-01"
    )
)
