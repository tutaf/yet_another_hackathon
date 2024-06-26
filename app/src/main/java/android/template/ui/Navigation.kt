/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.ui

import android.template.ui.feed.FeedScreen
import android.template.ui.feed.FeedScreenRouteDefinition
import android.template.ui.mymodel.PostRouteDefinition
import android.template.ui.mymodel.PostScreen
import android.template.ui.mymodel.PostScreenRoute
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.template.ui.onboarding.OnboardingScreen
import android.template.ui.onboarding.OnboardingViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainNavigation(viewModel: OnboardingViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val startDestination = if (viewModel.isFirstLaunch) "onboarding" else FeedScreenRouteDefinition

    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding") { OnboardingScreen(navController = navController) }
        composable(FeedScreenRouteDefinition) { FeedScreen(
            modifier = Modifier.padding(16.dp),
            onClick = {opportunity ->
                navController.navigate(PostScreenRoute(opp=opportunity))
            })
        }

        composable(PostRouteDefinition){
            PostScreen()
        }
    }
}