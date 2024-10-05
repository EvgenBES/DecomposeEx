package com.blackstone.decomposetest.decompose.koin.featureC

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FeatureCScreen(
    component: FeatureCComponent
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "FeatureC user name ${component.getUserName()}")

        Button(onClick = { component.navigateBackClicked() }) {
            Text(text = "Navigate back")
        }
    }
}