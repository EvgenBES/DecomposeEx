package com.blackstone.decomposetest.decompose.koin.featureC

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createFeatureCComponent(
    componentContext: ComponentContext,
    args: FeatureCArgs,
    navigateBack: () -> Unit,
): FeatureCComponent {
    return FeatureCComponent(args = args, dependencies = get(), navigateBack = navigateBack, componentContext = componentContext)
}

class FeatureCComponent(
    private val args: FeatureCArgs,
    private val dependencies: FeatureCDependencies,
    private val navigateBack: () -> Unit,
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    init {
        // load something
        dependencies.useCase3.load()
    }

    fun getUserName() = args.userName

    fun navigateBackClicked() {
        navigateBack()
    }
}
