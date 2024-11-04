package com.blackstone.decomposetest.decompose.koin.featureB

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeatureBScreen(
    component: FeatureBComponent
) {
    val state by component.uiState.collectAsState()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "FeatureB")

        Text(text = "Result: ${state.result}", modifier = Modifier.padding(16.dp))

        Button(onClick = { component.navigateBack() }) {
            Text(text = "Navigate back")
        }

        Button(onClick = { component.navigateToFeatureC() }) {
            Text(text = "Navigate to FeatureC")
        }
    }
}