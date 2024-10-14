package com.decompose.tab1.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decompose.navigation.ComponentScreen

internal class Tab1Screen(
    private val component: Tab1Component
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {
        TabView(
            modifier = modifier,
            onClick = component::navigateTo,
            onDetails = component::navigateToDetails,
        )
    }
}

@Composable
private fun TabView(
    modifier: Modifier,
    onClick: () -> Unit = {},
    onDetails: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text("Tab1")

                Button(onClick = onClick) {
                    Text("Click to Out List")
                }

                Button(onClick = onDetails) {
                    Text("Click to Details BS")
                }
            }
        }
    }
}
