package com.blackstone.decomposetest.decompose.koin.featureA

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

fun ComponentFactory.createFeatureAComponent(
    componentContext: ComponentContext
): FeatureAComponent {
    return get { parametersOf(componentContext) }
}

@Factory
class FeatureAComponent(
    private val dependencies: FeatureADependencies,
    private val navigator: FeatureANavigator,
    @InjectedParam componentContext: ComponentContext
) : ComponentContext by componentContext {

    fun navigateToFeatureB() {
        navigator.navigateToFeatureB()
    }

    fun navigateToFeatureC() {
        val userName = dependencies.repository.fetchUserName()
        navigator.navigateToFeatureC(userName)
    }
}