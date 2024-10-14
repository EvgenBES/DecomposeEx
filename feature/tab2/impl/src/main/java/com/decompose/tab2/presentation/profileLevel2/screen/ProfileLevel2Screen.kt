package com.decompose.tab2.presentation.profileLevel2.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.decompose.navigation.ComponentScreen

internal class ProfileLevel2Screen(
    private val component: ProfileLevel2Component
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Tab2: ProfileScreen > ProfileLevel2: index ${component.index}")

            Button(
                onClick = component::navigateToNext
            ) {
                Text("Click to next ProfileLevel2 index++")
            }
        }
    }
}