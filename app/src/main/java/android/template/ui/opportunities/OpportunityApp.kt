import android.annotation.SuppressLint
import android.template.data.models.ApiOpportunity
import android.template.ui.opportunities.OpportunityViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OpportunityApp(viewModel: OpportunityViewModel = hiltViewModel()) {
    val opportunities by viewModel.opportunities.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchOpportunities()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Opportunities") }
            )
        }
    ) {
        OpportunityList(opportunities = opportunities)
    }
}

@Composable
fun OpportunityList(opportunities: List<ApiOpportunity>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(opportunities) { opportunity ->
            OpportunityItem(opportunity = opportunity)
        }
    }
}

@Composable
fun OpportunityItem(opportunity: ApiOpportunity) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = opportunity.title, style = MaterialTheme.typography.h6)
            Text(text = "Category: ${opportunity.category}")
            Text(text = "Country: ${opportunity.country}")
            Text(text = "Degree: ${opportunity.degree}")
            Text(text = "Duration: ${opportunity.duration} months")
            Text(text = "Deadline: ${opportunity.deadline}")
            Text(text = "Requirements: ${opportunity.requirements.joinToString()}")
            Text(text = opportunity.content, style = MaterialTheme.typography.body2)
        }
    }
}
