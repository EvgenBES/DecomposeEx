package com.decompose.details.presentation.routing

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow

internal data class DetailsUiState(val productId: String)

internal class DetailsComponent(
    private val productId: String,
    private val router: DetailsRouter,
    componentContext: ComponentContext
): ComponentContext by componentContext {

    val uiState = MutableStateFlow(DetailsUiState(productId = productId))

}