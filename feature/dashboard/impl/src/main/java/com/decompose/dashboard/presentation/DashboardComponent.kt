package com.decompose.dashboard.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.decompose.dashboard.api.NavTab
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.Destination
import com.decompose.routing.DefaultTabRouter
import com.decompose.routing.TabRouter
import org.koin.dsl.module

class DashboardComponent(
    private val dependencies: DashboardDependencies,
    private val componentFactory: ComponentFactory,
    private val componentContext: ComponentContext,
): ComponentContext by componentContext  {

    private val bottomMenu: List<NavTab> = dependencies.navTabs.filter { it.isAvailable }.sortedBy { it.position }
    private val startDestination = bottomMenu.first().destination

    private val childNavigation = StackNavigation<Destination>()
    private val route = DefaultTabRouter(childNavigation)
    private val module = module { single<TabRouter> { route } }

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = dependencies.serializerProvider.serializer,
        initialStack = { listOf(startDestination) },
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    private fun childBuilder(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }

    fun fetchTabs() = bottomMenu

    fun onTabClicked(menu: NavTab) {
        route.push(menu.destination)
    }
}


