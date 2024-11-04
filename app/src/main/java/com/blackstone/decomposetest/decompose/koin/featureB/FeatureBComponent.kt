package com.blackstone.decomposetest.decompose.koin.featureB

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.blackstone.decomposetest.decompose.store.ComponentStore
import com.blackstone.decomposetest.decompose.store.ResultContainer
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
        setStoreResultListener<String>("YOUR_RESULT_KEY") { result ->
            uiState.update { it.copy(result = result.value) }
        }
    }

    fun navigateBack() {
        navigator.navigateBack()
    }

    fun navigateToFeatureC() {
        val userName = dependencies.repository.fetchUserName()
        navigator.navigateToFeatureC(userName)
    }
}