package com.decompose.tab2.presentation.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.decompose.navigation.ComponentScreen

internal class ProfileScreen(
    private val component: ProfileComponent
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Tab2: ProfileScreen")

            Button(
                onClick = component::navigateTo
            ) {
                Text("Click to next")
            }
        }
    }
}