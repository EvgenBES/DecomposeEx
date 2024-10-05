package com.decompose.cards.presentation.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.cards.presentation.routing.CardsRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

internal data class CardsUiState(
    val cards: List<String> = emptyList()
)

internal class CardsComponent(
    private val router: CardsRouter,
    componentContext: ComponentContext
): ComponentContext by componentContext {

    val uiState = MutableStateFlow(CardsUiState())

    init {
        loadData()
    }

    private fun loadData() {
        val cards = listOf(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
        )

        uiState.update { it.copy(cards = cards) }
    }

    fun selected(uuid: String) {
        router.navigateToDetails(productId = uuid)
    }

}