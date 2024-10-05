package com.decompose.details.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.details.presentation.screen.DetailsComponent
import com.decompose.details.presentation.screen.DetailsContent
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentContent
import com.decompose.navigation.Destination
import kotlinx.serialization.Serializable
import org.koin.core.component.get

@Serializable
class DetailsDestination(private val productId: String) : Destination {
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
    override val content: ComponentContent = DetailsContent(
        DetailsComponent(
            productId = productId,
            router = router,
            componentContext = componentContext
        )
    )
}

