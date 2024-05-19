
package android.template.ui

import android.template.ui.feed.FeedScreen
import android.template.ui.feed.FeedScreenRouteDefinition
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            FeedScreen(
                modifier = Modifier.padding(16.dp),
                onClick = {opportunity ->
//                    navController.navigate(categoryScreenRoute(category.name))
                    Log.i(">>>>>categoryName", opportunity.title)
                },
                )
        }


    }
}
