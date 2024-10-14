package com.decompose.details.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.details.api.DetailsRoute
import com.decompose.details.presentation.screen.DetailsComponent
import com.decompose.details.presentation.screen.DetailsScreen
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class DetailsRouteImpl : DetailsRoute {
    override fun navigate(context: String): Destination {
        return DetailsDestination(productId = context)
    }
}

@Serializable
internal class DetailsDestination(private val productId: String) : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return DetailsContentProvider(
            productId = productId,
            router = componentFactory.get(),
            componentContext = componentContext
        )
    }
}

private class DetailsContentProvider(
    private val productId: String,
    private val router: DetailsRouter,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = DetailsScreen(
        DetailsComponent(
            productId = productId,
            router = router,
            componentContext = componentContext
        )
    )
}