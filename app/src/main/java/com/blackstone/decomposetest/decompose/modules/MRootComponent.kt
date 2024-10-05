package com.blackstone.decomposetest.decompose.modules

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.decompose.cards.api.CardsRoute
import com.decompose.cards.presentation.routing.CardsDestination
import com.decompose.common.NavControllers
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.Destination
import com.decompose.routing.RootRouter
import com.decompose.routing.Router
import org.koin.core.component.get
import org.koin.dsl.module

class MRootComponent(
    private val componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val initDestination = componentFactory.get<CardsRoute>().navigate()

    private val childNavigation = StackNavigation<Destination>()
    private val slotNavigation = SlotNavigation<Destination>()
    private val route = RootRouter(navControllers = NavControllers(childNavigation, slotNavigation))
    private val module = module { single<Router> { route } }

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = DestinationSerializer,
        initialStack = { listOf(initDestination) },
        handleBackButton = true,
        childFactory = ::child
    )

    val slot: Value<ChildSlot<*, ComponentChild>> = childSlot(
        source = slotNavigation,
        serializer = DestinationSerializer,
        handleBackButton = true,
        childFactory = ::slot
    )

    fun dismiss() {
        slotNavigation.dismiss()
    }

    private fun child(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }

    private fun slot(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }
}


