package com.decompose.host

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
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.routing.Destination
import com.decompose.routing.Router
import org.koin.core.component.get
import org.koin.core.module.Module

abstract class DefaultHostComponent(
    private val startDestination: Destination,
    private val componentFactory: ComponentFactory,
    serializerProvider: SerializerProvider = componentFactory.get(),
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    protected val childNavigation = StackNavigation<Destination>()
    protected val slotNavigation = SlotNavigation<Destination>()
    abstract val route: Router
    abstract val module: Module

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = serializerProvider.serializer,
        initialStack = { listOf(startDestination) },
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    val slot: Value<ChildSlot<*, ComponentChild>> = childSlot(
        source = slotNavigation,
        serializer = serializerProvider.serializer,
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    fun dismiss() {
        slotNavigation.dismiss()
    }

    private fun childBuilder(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }
}