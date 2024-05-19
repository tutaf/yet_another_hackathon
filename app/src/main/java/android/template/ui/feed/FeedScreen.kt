
package android.template.ui.feed

import android.template.data.erasmulApi.models.ApiOpportunity
import android.template.ui.theme.MyApplicationTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel


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
        CenterAlignedTopAppBar(title = { Text("opportunnity", style = MaterialTheme.typography.titleLarge)  }, modifier = Modifier.fillMaxWidth()  )
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
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp) // Rounded corners with shadows
                        )
                        .clickable { onClick(opportunity) }

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = opportunity.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        FeedScreen(emptyList(), onClick = {})
    }
}

