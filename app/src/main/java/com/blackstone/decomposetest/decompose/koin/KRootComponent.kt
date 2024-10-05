package com.blackstone.decomposetest.decompose.koin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.blackstone.decomposetest.decompose.koin.featureA.FeatureAComponent
import com.blackstone.decomposetest.decompose.koin.featureA.FeatureAScreen
import com.blackstone.decomposetest.decompose.koin.featureA.createFeatureAComponent
import com.blackstone.decomposetest.decompose.koin.featureB.FeatureBComponent
import com.blackstone.decomposetest.decompose.koin.featureB.FeatureBScreen
import com.blackstone.decomposetest.decompose.koin.featureB.createFeatureBComponent
import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCComponent
import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCScreen
import com.blackstone.decomposetest.decompose.koin.featureC.createFeatureCComponent
import com.decompose.di.ComponentFactory
import org.koin.dsl.module

class KRootComponent(
    private val componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val childNavigation = StackNavigation<KRootConfig.Child>()
    private val module = module { single { childNavigation } }

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    val stack: Value<ChildStack<*, KRootRootChild>> = childStack(
        source = childNavigation,
        serializer = KRootConfig.Child.serializer(),
        initialStack = { listOf(KRootConfig.Child.FeatureA) },
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: KRootConfig.Child,
        componentContext: ComponentContext,
    ): KRootRootChild {
        return when (config) {
            is KRootConfig.Child.FeatureA -> KRootRootChild.ScreenA(componentFactory.createFeatureAComponent(componentContext))
            is KRootConfig.Child.FeatureB -> KRootRootChild.ScreenB(componentFactory.createFeatureBComponent(componentContext))
            is KRootConfig.Child.FeatureC -> KRootRootChild.ScreenC(
                componentFactory.createFeatureCComponent(
                    componentContext = componentContext,
                    args = config.args,
                    navigateBack = { childNavigation.pop() } // can be
                )
            )
        }
    }
}

sealed interface KRootRootChild {
    class ScreenA(val component: FeatureAComponent) : KRootRootChild
    class ScreenB(val component: FeatureBComponent) : KRootRootChild
    class ScreenC(val component: FeatureCComponent) : KRootRootChild
}

@Composable
fun KRootContent(component: KRootComponent) {
    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide() + scale()),
    ) {
        when (val child = it.instance) {
            is KRootRootChild.ScreenA -> FeatureAScreen(component = child.component)
            is KRootRootChild.ScreenB -> FeatureBScreen(component = child.component)
            is KRootRootChild.ScreenC -> FeatureCScreen(component = child.component)
        }
    }
}
