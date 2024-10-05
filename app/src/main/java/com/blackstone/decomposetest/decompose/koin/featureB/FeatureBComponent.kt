package com.blackstone.decomposetest.decompose.koin.featureB

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createFeatureBComponent(
    componentContext: ComponentContext
): FeatureBComponent {
    return FeatureBComponent(dependencies = get(), navigator = get(), componentContext = componentContext)
}

class FeatureBComponent(
    private val dependencies: FeatureBDependencies,
    private val navigator: FeatureBNavigator,
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    fun navigateBack() {
        navigator.navigateBack()
    }

    fun navigateToFeatureC() {
        val userName = dependencies.repository.fetchUserName()
        navigator.navigateToFeatureC(userName)
    }
}