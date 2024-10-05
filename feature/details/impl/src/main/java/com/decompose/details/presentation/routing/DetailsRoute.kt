package com.decompose.details.presentation.routing

import com.decompose.details.api.DetailsRoute
import com.decompose.navigation.Destination
import org.koin.core.annotation.Factory

@Factory
internal class DetailsRouteImpl : DetailsRoute {
    override fun navigate(context: String): Destination {
        return DetailsDestination(productId = context)
    }
}
