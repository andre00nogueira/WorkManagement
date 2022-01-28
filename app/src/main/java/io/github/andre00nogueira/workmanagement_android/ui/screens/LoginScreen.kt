package io.github.andre00nogueira.workmanagement_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import io.github.andre00nogueira.workmanagement_android.R
import io.github.andre00nogueira.workmanagement_android.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    val inProgress = remember { viewModel.uiState.inProgress }

    val (username, setUsername) = remember { viewModel.uiState.username }
    val (password, setPassword) = remember { viewModel.uiState.password }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (inProgress.value) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome_label),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 50.dp)
                    )
                    OutlinedTextField(
                        label = { Text(text = stringResource(id = R.string.username_label)) },
                        value = username,
                        onValueChange = setUsername,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        label = { Text(text = stringResource(id = R.string.password_label)) },
                        value = password,
                        onValueChange = setPassword,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            autoCorrect = false,
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        onClick = { viewModel.onSubmit(navController) },
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_label),
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.create_account_label),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .clickable {
                                navController.navigate("register")
                            }
                    )
                }
            }
        }
    }
}