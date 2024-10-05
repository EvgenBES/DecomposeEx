package com.blackstone.decomposetest.decompose.modules

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.decompose.cards.presentation.routing.CardsDestination
import com.decompose.common.NavControllers
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.Destination
import org.koin.dsl.module

class MRootComponent(
    private val componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val childNavigation = StackNavigation<Destination>()
    private val slotNavigation = SlotNavigation<Destination>()
    private val navControllers = NavControllers(childNavigation, slotNavigation)
    private val module = module { single { navControllers } }

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = DestinationSerializer,
        initialStack = { listOf(CardsDestination()) },
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }
}


