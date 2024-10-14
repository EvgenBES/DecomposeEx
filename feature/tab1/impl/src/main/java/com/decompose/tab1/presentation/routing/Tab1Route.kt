package com.decompose.tab1.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import com.decompose.tab2.api.Tab1Route
import com.decompose.tab1.presentation.screen.Tab1Component
import com.decompose.tab1.presentation.screen.Tab1Screen
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class Tab1RouteImpl : Tab1Route {
    override fun navigate(): Destination {
        return Tab1Destination()
    }
}

@Serializable
class Tab1Destination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return Tab1ContentProvider(
            router = componentFactory.get(),
            componentContext = componentContext
        )
    }
}

private class Tab1ContentProvider(
    router: Tab1Router,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = Tab1Screen(
        Tab1Component(
            router = router,
            componentContext = componentContext
        )
    )
}