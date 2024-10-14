package com.decompose.cards.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.decompose.navigation.ComponentScreen

internal class CardsScreen(
    private val component: CardsComponent
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {
        val state by component.uiState.collectAsState()

        CardsView(
            state = state,
            modifier = modifier,
            onSelect = component::selected
        )
    }
}

@Composable
private fun CardsView(
    state: CardsUiState,
    modifier: Modifier,
    onSelect: (String) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize()) {
        state.cards.fastForEach { item ->
            CardsItem(uuid = item, onClick = onSelect)
        }
    }
}

@Composable
private fun CardsItem(
    uuid: String,
    onClick: (String) -> Unit,
) {
    Surface(onClick = { onClick(uuid) }) {
        Text(text = uuid, modifier = Modifier.padding(16.dp))
    }
}
