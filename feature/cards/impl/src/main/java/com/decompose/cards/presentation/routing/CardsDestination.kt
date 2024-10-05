package com.decompose.cards.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.cards.presentation.screen.CardsComponent
import com.decompose.cards.presentation.screen.CardsContent
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.Destination
import kotlinx.serialization.Serializable
import org.koin.core.component.get

@Serializable
class CardsDestination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return ComponentChild {
            CardsContent(
                CardsComponent(
                    router = componentFactory.get(),
                    componentContext = componentContext
                )
            )
        }
    }
}