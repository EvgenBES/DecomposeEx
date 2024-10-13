package com.decompose.cards.presentation.routing

import com.decompose.cards.api.CardsRoute
import com.decompose.routing.Destination
import org.koin.core.annotation.Factory

@Factory
internal class CardsRouteImpl : CardsRoute {
    override fun destination(): Destination {
        return CardsDestination()
    }
}
