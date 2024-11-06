package com.blackstone.decomposetest.decompose.koin.featureC

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.blackstone.decomposetest.decompose.koin.featureA.Result1
import com.blackstone.decomposetest.decompose.store.ComponentStore
import com.blackstone.decomposetest.decompose.store.setStoreResult
import com.blackstone.decomposetest.decompose.store.setStoreResultListener
import com.decompose.di.ComponentFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.get

fun ComponentFactory.createFeatureCComponent(
    componentContext: ComponentContext,
    args: FeatureCArgs,
    navigateBack: () -> Unit,
): FeatureCComponent {
    return FeatureCComponent(
        args = args,
        dependencies = get(),
        componentStore = get(),
        navigateBack = navigateBack,
        componentContext = componentContext
    )
}

data class FeatureCUiState(
    val inputText: String = ""
)

class FeatureCComponent(
    private val args: FeatureCArgs,
    private val dependencies: FeatureCDependencies,
    private val componentStore: ComponentStore,
    private val navigateBack: () -> Unit,
    componentContext: ComponentContext
) : ComponentContext by componentContext, ComponentStore by componentStore {

    val uiState = MutableStateFlow(FeatureCUiState())

    init {
        // load something
        dependencies.useCase3.load()

        setStoreResultListener<Result1>("KEY1") {
            Log.e("AAQQ", "Result1: $it")
        }

        setStoreResultListener<String>("KEY2") {
            Log.e("AAQQ", "Result2: $it")
        }

        setStoreResultListener<Boolean>("KEY3") {
            Log.e("AAQQ", "Result3: $it")
        }
    }

    fun updateInput(value: String) = uiState.update { it.copy(inputText = value) }

    fun getUserName() = args.userName

    fun navigateBackClicked() {
        navigateBack()
    }
}
