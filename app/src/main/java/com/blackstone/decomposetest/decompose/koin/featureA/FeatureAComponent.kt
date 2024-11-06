package com.blackstone.decomposetest.decompose.koin.featureA

import com.arkivanov.decompose.ComponentContext
import com.blackstone.decomposetest.decompose.store.ComponentStore
import com.blackstone.decomposetest.decompose.store.setStoreResult
import com.decompose.di.ComponentFactory
import kotlinx.serialization.Serializable
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.get

fun ComponentFactory.createFeatureAComponent(
    componentContext: ComponentContext
): FeatureAComponent {
    return FeatureAComponent(
        dependencies = get(),
        navigator = get(),
        componentStore = get(),
        componentContext = componentContext
    )
}

@Serializable
data class Result1(val age: Int, val name: String)

class FeatureAComponent(
    private val dependencies: FeatureADependencies,
    private val navigator: FeatureANavigator,
    private val componentStore: ComponentStore,
    @InjectedParam componentContext: ComponentContext
) : ComponentContext by componentContext, ComponentStore by componentStore {

    fun navigateToFeatureB() {
        setStoreResult("KEY1", result = Result1(16, "Eugene"))

        navigator.navigateToFeatureB()
    }

    fun navigateToFeatureC() {
        val userName = dependencies.repository.fetchUserName()
        navigator.navigateToFeatureC(userName)
    }
}