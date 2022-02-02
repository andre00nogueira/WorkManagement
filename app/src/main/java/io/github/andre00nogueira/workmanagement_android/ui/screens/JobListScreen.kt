package io.github.andre00nogueira.workmanagement_android.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.github.andre00nogueira.workmanagement_android.R
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.ui.elements.JobItem
import io.github.andre00nogueira.workmanagement_android.viewmodels.JobListViewModel

@ExperimentalFoundationApi
@Composable
fun JobListScreen(
    navController: NavHostController,
    viewModel: JobListViewModel = viewModel()
) {
    val inProgress = remember { viewModel.uiState.inProgress }

    val refresh = remember { viewModel.uiState.refresh }

    val jobs = remember { viewModel.uiState.jobs }

    LaunchedEffect(refresh.value) {
        viewModel.getJobList()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.CreateJobRoute.route) }) {
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
                    text = stringResource(id = R.string.job_title_label),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
            when {
                inProgress.value -> item { CircularProgressIndicator() }

                jobs.value.isEmpty() -> item { Text(text = stringResource(id = R.string.no_jobs)) }

                else -> items(jobs.value) {
                    JobItem(job = it, onDelete = { viewModel.deleteJob(it) })
                }
            }
        }
    }
}