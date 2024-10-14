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
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.routing.Destination

class DefaultHostComponent(
    private val startDestination: Destination,
    private val componentFactory: ComponentFactory,
    private val childNavigation: StackNavigation<Destination>,
    private val slotNavigation: SlotNavigation<Destination>,
    serializerProvider: SerializerProvider,
    componentContext: ComponentContext
) : HostComponent, ComponentContext by componentContext {

    override val stack: Value<ChildStack<*, ComponentChild>> = childStack(
        source = childNavigation,
        serializer = serializerProvider.serializer,
        initialStack = { listOf(startDestination) },
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    override val slot: Value<ChildSlot<*, ComponentChild>> = childSlot(
        source = slotNavigation,
        serializer = serializerProvider.serializer,
        handleBackButton = true,
        childFactory = ::childBuilder
    )

    override fun dismiss() {
        slotNavigation.dismiss()
    }

    private fun childBuilder(
        destination: Destination,
        componentContext: ComponentContext,
    ): ComponentChild {
        return destination.factory(componentContext, componentFactory)
    }
}