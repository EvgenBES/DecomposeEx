package com.blackstone.decomposetest.decompose.koin.featureA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FeatureAScreen(
    component: FeatureAComponent
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "FeatureA")

        Button(onClick = { component.navigateToFeatureB() }) {
            Text(text = "Navigate to FeatureB")
        }

        Button(onClick = { component.navigateToFeatureC() }) {
            Text(text = "Navigate to FeatureC")
        }
    }
}