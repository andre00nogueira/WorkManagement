package io.github.andre00nogueira.workmanagement_android.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.github.andre00nogueira.workmanagement_android.R
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.ui.elements.JobItem
import io.github.andre00nogueira.workmanagement_android.viewmodels.CreateJobViewModel
import io.github.andre00nogueira.workmanagement_android.viewmodels.JobListViewModel

@ExperimentalFoundationApi
@Composable
fun CreateJobScreen(
    navController: NavHostController,
    viewModel: CreateJobViewModel = viewModel()
) {
    val inProgress = remember { viewModel.uiState.inProgress }

    val (jobName, setJobName) = remember { viewModel.uiState.jobName }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.CreateJobRoute.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add Job"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            stickyHeader {
                Text(
                    text = stringResource(id = R.string.create_job_title_label),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
            when {
                inProgress.value -> item { CircularProgressIndicator() }

                else -> item {
                    OutlinedTextField(
                        label = { Text(text = stringResource(id = R.string.jobname_label)) },
                        value = jobName,
                        onValueChange = setJobName,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        onClick = { viewModel.onSubmit(navController) },
                    ) {
                        Text(
                            text = stringResource(id = R.string.create),
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}