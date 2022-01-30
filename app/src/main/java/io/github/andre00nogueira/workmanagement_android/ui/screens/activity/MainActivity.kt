package io.github.andre00nogueira.workmanagement_android.ui.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.ui.screens.JobListScreen
import io.github.andre00nogueira.workmanagement_android.ui.screens.LoginScreen
import io.github.andre00nogueira.workmanagement_android.ui.screens.RegisterScreen
import io.github.andre00nogueira.workmanagement_android.ui.theme.WorkManagementandroidTheme
import io.github.andre00nogueira.workmanagement_android.viewmodels.JobListViewModel
import io.github.andre00nogueira.workmanagement_android.viewmodels.LoginViewModel
import io.github.andre00nogueira.workmanagement_android.viewmodels.RegisterViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagementandroidTheme {
                NavigationComponent()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginRoute.route
    ) {
        composable(Routes.LoginRoute.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController, viewModel)
        }
        composable(Routes.RegisterRoute.route) {
            val viewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(navController, viewModel)
        }
        composable(Routes.JobListRoute.route) {
            val viewModel = hiltViewModel<JobListViewModel>()
            JobListScreen(navController, viewModel)
        }
    }
}