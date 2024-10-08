package com.decompose.tab2.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentContent
import com.decompose.navigation.Destination
import com.decompose.tab2.api.Tab2Route
import com.decompose.tab2.presentation.screen.Tab2Component
import com.decompose.tab2.presentation.screen.Tab2Content
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class Tab2RouteImpl : Tab2Route {
    override fun navigate(): Destination {
        return Tab2Destination()
    }
}

@Serializable
class Tab2Destination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return Tab2ContentProvider(
            router = componentFactory.get(),
            componentContext = componentContext
        )
    }
}

private class Tab2ContentProvider(
    router: Tab2Router,
    componentContext: ComponentContext
) : ComponentChild {
    override val content: ComponentContent = Tab2Content(
        Tab2Component(
            router = router,
            componentContext = componentContext
        )
    )
}