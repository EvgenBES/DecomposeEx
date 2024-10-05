package com.blackstone.decomposetest.decompose.miltibottomsheet

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

class BottomSheetComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val nav = StackNavigation<BSConfig>()

    val stack: Value<ChildStack<*, Child>> = childStack(
        source = nav,
        serializer = BSConfig.serializer(),
        initialStack = { listOf(BSConfig.Empty) },
        handleBackButton = true,
    ) { config, childContext ->
        when (config) {
            BSConfig.Empty -> Child.Empty(BSChildComponent(componentContext))
            BSConfig.BSOne -> Child.Child1(BSChildComponent(componentContext))
            BSConfig.BSTwo -> Child.Child2(BSChildComponent(componentContext))
            BSConfig.BSThree -> Child.Child3(BSChildComponent(componentContext))
        }
    }

    fun showBottomSheetOne() {
        nav.pushNew(BSConfig.BSOne)
    }

    fun navigateBack() {
        nav.pop()
    }

    fun pushConfig(config: BSConfig) {
        nav.pushNew(config)
    }

    @Serializable
    sealed class BSConfig {
        @Serializable
        data object Empty : BSConfig()

        @Serializable
        data object BSOne : BSConfig()

        @Serializable
        data object BSTwo : BSConfig()

        @Serializable
        data object BSThree : BSConfig()
    }

    sealed class Child {
        class Empty(val component: BSChildComponent) : Child()
        class Child1(val component: BSChildComponent) : Child()
        class Child2(val component: BSChildComponent) : Child()
        class Child3(val component: BSChildComponent) : Child()
    }
}