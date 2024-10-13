package com.decompose.dashboard.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import com.decompose.dashboard.api.DashboardNavTab
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.routing.Destination

class DashboardComponent(
    private val dependencies: DashboardDependencies,
    private val componentFactory: ComponentFactory,
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val bottomMenu: List<DashboardNavTab> = dependencies.navTabs.sortedBy { it.position }
    private val startDestination = bottomMenu.first().destination
    private val childNavigation = StackNavigation<Destination>()
    private val backCallback = BackCallback { childNavigation.popTo(0) }

    val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = dependencies.serializerProvider.serializer,
        initialStack = { listOf(startDestination) },
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    init {
        registerBackHandler()
    }

    private fun registerBackHandler() {
        backHandler.register(backCallback)

        stack.subscribe {
            val isFirstTab = it.active.configuration == startDestination
            backCallback.isEnabled = !isFirstTab
        }
    }

    private fun childBuilder(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }

    fun fetchTabs() = bottomMenu

    fun onTabClicked(menu: DashboardNavTab) {
        childNavigation.bringToFront(menu.destination)
    }
}


