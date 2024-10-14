package com.decompose.tab2.presentation.host

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import com.decompose.tab2.api.Tab2Route
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory

@Factory
internal class Tab2RouteImpl : Tab2Route {
    override fun destination(): Destination {
        return Tab2Destination()
    }
}

@Serializable
internal class Tab2Destination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return Tab2ContentProvider(
            componentFactory = componentFactory,
            componentContext = componentContext
        )
    }
}

private class Tab2ContentProvider(
    componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = Tab2Host(
        Tab2Component(
            componentFactory = componentFactory,
            componentContext = componentContext
        )
    )
}