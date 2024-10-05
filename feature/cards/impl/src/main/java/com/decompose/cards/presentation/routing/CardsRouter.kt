package com.decompose.cards.presentation.routing

import android.util.Log
import com.decompose.details.api.DetailsRoute
import org.koin.core.annotation.Factory

@Factory
internal class CardsRouter(
    private val detailsRoute: DetailsRoute,
) {
    fun navigateToDetails(productId: String) {
        val destination = detailsRoute.navigate(context = productId)
        Log.e("AAQQ", "$destination")
    }
}