package com.decompose.tab2.presentation.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.decompose.di.ComponentFactory
import com.decompose.host.DefaultHostComponent
import com.decompose.host.HostComponent
import com.decompose.navigation.ComponentChild
import com.decompose.routing.Destination
import com.decompose.tab2.api.Tab2Router
import com.decompose.tab2.presentation.profile.routing.ProfileDestination
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

internal class Tab2Component(
    private val componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : HostComponent, ComponentContext by componentContext {

    private val childNavigation = StackNavigation<Destination>()
    private val slotNavigation = SlotNavigation<Destination>()
    private val router: Tab2Router = Tab2RouterImpl(childNavigation, slotNavigation)
    private val module: Module = module { single<Tab2Router> { router } }

    init {
        componentFactory.loadModule(module)
        componentContext.doOnDestroy { componentFactory.unloadModule(module) }
    }

    private val host: HostComponent = DefaultHostComponent(
        startDestination = ProfileDestination(),
        componentFactory = componentFactory,
        childNavigation = childNavigation,
        slotNavigation = slotNavigation,
        serializerProvider = componentFactory.get(),
        componentContext = componentContext
    )

    override val stack: Value<ChildStack<*, ComponentChild>> = host.stack
    override val slot: Value<ChildSlot<*, ComponentChild>> = host.slot
    override fun dismiss() = host.dismiss()
}