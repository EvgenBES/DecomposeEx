package com.decompose.tab1.presentation.routing

import com.decompose.cards.api.CardsRoute
import com.decompose.details.api.DetailsRoute
import com.decompose.routing.Router
import org.koin.core.annotation.Factory
import java.util.UUID

internal interface Tab1Router {
    fun navigateTo()
    fun navigateToDetails()
}

@Factory
internal class Tab1RouterImpl(
    private val router: Router,
    private val cardsRoute: CardsRoute,
    private val detailsRoute: DetailsRoute,
) : Tab1Router {
    override fun navigateTo() {
        router.push(cardsRoute.destination())
    }

    override fun navigateToDetails() {
        router.activate(detailsRoute.navigate(UUID.randomUUID().toString()))
    }
}

