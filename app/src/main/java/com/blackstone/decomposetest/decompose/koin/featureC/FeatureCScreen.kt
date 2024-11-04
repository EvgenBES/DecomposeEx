package com.blackstone.decomposetest.decompose.koin.featureC

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeatureCScreen(
    component: FeatureCComponent
) {
    val state by component.uiState.collectAsState()

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "FeatureC user name ${component.getUserName()}")

        Button(onClick = { component.navigateBackClicked() }) {
            Text(text = "Navigate back with result")
        }

        OutlinedTextField(
            value = state.inputText,
            onValueChange = { component.updateInput(it) },
            modifier = Modifier.padding(16.dp)
        )
    }
}