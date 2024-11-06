package com.blackstone.decomposetest.decompose.koin.featureB

import com.arkivanov.decompose.ComponentContext
import com.blackstone.decomposetest.decompose.store.ComponentStore
import com.blackstone.decomposetest.decompose.store.setStoreResult
import com.blackstone.decomposetest.decompose.store.setStoreResultListener
import com.decompose.di.ComponentFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.get

fun ComponentFactory.createFeatureBComponent(
    componentContext: ComponentContext
): FeatureBComponent {
    return FeatureBComponent(
        dependencies = get(),
        navigator = get(),
        componentStore = get(),
        componentContext = componentContext
    )
}

data class FeatureBUiState(
    val result: String = ""
)

class FeatureBComponent(
    private val dependencies: FeatureBDependencies,
    private val navigator: FeatureBNavigator,
    private val componentStore: ComponentStore,
    componentContext: ComponentContext
) : ComponentContext by componentContext, ComponentStore by componentStore {

    val uiState = MutableStateFlow(FeatureBUiState())

    init {
        setStoreResultListener<Boolean>("YOUR_RESULT_KEY") { result ->
            uiState.update { it.copy(result = result.toString()) }
        }
    }

    fun navigateBack() {
        navigator.navigateBack()
    }

    fun navigateToFeatureC() {
        setStoreResult("KEY2", result = "blue smile")
        setStoreResult("KEY3", result = false)
        val userName = dependencies.repository.fetchUserName()
        navigator.navigateToFeatureC(userName)
    }
}