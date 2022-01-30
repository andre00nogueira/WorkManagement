package io.github.andre00nogueira.workmanagement_android.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.andre00nogueira.workmanagement_android.R
import io.github.andre00nogueira.workmanagement_android.model.Job

@Composable
fun JobItem(job: Job, onDelete: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = job.jobName, modifier = Modifier.padding(10.dp))
            IconButton(onClick = onDelete) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete Job"
                )
            }
        }
    }
}