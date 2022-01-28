package io.github.andre00nogueira.workmanagement_android.ui.theme.screens.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.ui.theme.WorkManagementandroidTheme
import io.github.andre00nogueira.workmanagement_android.ui.theme.screens.LoginScreen
import io.github.andre00nogueira.workmanagement_android.viewmodels.LoginViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagementandroidTheme {
                NavigationComponent()
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginRoute.route
    ) {
        composable(Routes.LoginRoute.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(viewModel)
        }
    }
}