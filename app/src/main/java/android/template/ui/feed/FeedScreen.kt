
package android.template.ui.feed

import android.template.MyApplication
import android.template.R
import android.template.data.models.ApiOpportunity
import android.template.ui.opportunities.OpportunityViewModel
import android.template.ui.theme.MyApplicationTheme
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
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.text.SimpleDateFormat
import java.util.Locale

const val FeedScreenRouteDefinition = "feed"


@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    onClick: (ApiOpportunity) -> Unit

) {
    val opportunities by viewModel.opportunities.observeAsState(emptyList())
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

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = MaterialTheme.colorScheme.onPrimaryContainer, // Adjust the background color as needed
            modifier = Modifier.weight(6f),
            shape = RoundedCornerShape(12.dp), // Adjust the corner radius as needed

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

            IconButton(
                onClick = { text = "" },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {
                //Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
        Spacer(modifier = Modifier.width(8.dp))


        Surface(
            color = MaterialTheme.colorScheme.surface,
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
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            OpportunityDetails(opp = opp)
            OpportunityTags(opp = opp)

            //Spacer(modifier = Modifier.width(8.dp)) // Add space between the tags and details

        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OpportunityTags(opp: ApiOpportunity) {
    val buttonStyle = Modifier.padding(horizontal = 2.dp) // Adjust padding for button

    val items = listOf(
        opp.degree,
        opp.category,
        ""+opp.duration+ " months"
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add space of 8.dp between elements
    ) {
        items(items) { item ->
//            OutlinedButton(onClick = { }, modifier = buttonStyle) {
//                Text(
//                    text = item,
//                    fontSize = 12.sp
//                )
//            }
            SuggestionChip(onClick = { }, label = { Text(item) })

        }
    }
}

fun formatDeadline(deadline: String): String {
    val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
    val date = inputFormat.parse(deadline)
    return if (date != null) outputFormat.format(date) else deadline
}

@Composable
fun OpportunityDetails(opp: ApiOpportunity) {
    val textStyle = TextStyle(color = Color.Gray) // Define a common text style

    val details = listOf(
        //"Duration: ${opp.duration} days",
        //"| Application deadline: ",
        "Application deadline: ",
        formatDeadline(opp.deadline)


    )
    Column {
        Spacer(modifier = Modifier.height(6.dp))

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
}

//@Preview(showBackground = true)
//@Composable
//private fun DefaultPreview() {
//    MyApplicationTheme {
//        FeedScreen(sampleOpportunities, onClick = {})
//    }
//}


