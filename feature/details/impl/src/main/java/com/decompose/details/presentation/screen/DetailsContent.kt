package com.decompose.details.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decompose.navigation.ComponentContent

internal class DetailsContent(
    private val component: DetailsComponent
) : ComponentContent {
    @Composable
    override fun Content(modifier: Modifier) {
        val state by component.uiState.collectAsState()

        DetailsView(
            state = state,
            modifier = modifier
        )
    }
}

@Composable
private fun DetailsView(
    state: DetailsUiState,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Selected item: \n ${state.productId}")
        }
    }
}
