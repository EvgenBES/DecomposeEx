package com.decompose.cards.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.cards.presentation.screen.CardsComponent
import com.decompose.cards.presentation.screen.CardsScreen
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import kotlinx.serialization.Serializable
import org.koin.core.component.get

@Serializable
class CardsDestination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return CardsDetailsContentProvider(
            router = componentFactory.get(),
            componentContext = componentContext
        )
    }
}

private class CardsDetailsContentProvider(
    router: CardsRouter,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = CardsScreen(
        CardsComponent(
            router = router,
            componentContext = componentContext
        )
    )
}