
package android.template.ui

import android.template.ui.feed.FeedScreen
import android.template.ui.feed.FeedScreenRouteDefinition
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FeedScreenRouteDefinition,
        exitTransition = { ExitTransition.None },
        enterTransition =  { EnterTransition.None }
    ) {

        composable(FeedScreenRouteDefinition){
            FeedScreen(modifier = Modifier.padding(16.dp))
        }


    }
}
