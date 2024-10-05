package com.blackstone.decomposetest.decompose.koin.featureB

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FeatureBScreen(
    component: FeatureBComponent
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "FeatureB")

        Button(onClick = { component.navigateBack() }) {
            Text(text = "Navigate back")
        }

        Button(onClick = { component.navigateToFeatureC() }) {
            Text(text = "Navigate to FeatureC")
        }
    }
}