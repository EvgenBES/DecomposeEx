package com.decompose.cards.presentation.routing

import com.decompose.details.api.DetailsRoute
import com.decompose.routing.Router
import org.koin.core.annotation.Factory

@Factory
internal class CardsRouter(
    private val router: Router,
    private val detailsRoute: DetailsRoute,
) {
    fun navigateToDetails(productId: String) {
        router.push(detailsRoute.navigate(context = productId))
    }
}