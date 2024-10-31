package com.decompose.details.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decompose.navigation.ComponentScreen

internal class DetailsScreen(
    private val component: DetailsComponent
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {
        val state by component.uiState.collectAsState()

        DetailsView(
            state = state,
            modifier = modifier,
            onClick = component::navigateTo,
            onDismiss = component::dismiss,
        )
    }
}

@Composable
private fun DetailsView(
    state: DetailsUiState,
    modifier: Modifier,
    onClick: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Selected item: \n ${state.productId}")

                Button(onClick = onClick) {
                    Text("Click by Yana")
                }

                Button(onClick = onDismiss) {
                    Text("On Dismiss")
                }
            }
        }
    }
}
