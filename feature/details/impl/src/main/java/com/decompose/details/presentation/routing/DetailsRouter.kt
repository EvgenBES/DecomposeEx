package com.decompose.details.presentation.routing

import com.decompose.details.api.DetailsRoute
import com.decompose.routing.Router
import org.koin.core.annotation.Factory
import java.util.UUID

internal interface DetailsRouter {
    fun navigateTo()
    fun dismiss()
}

@Factory
internal class DetailsRouterImpl(
    private val router: Router,
    private val detailsRoute: DetailsRoute
) : DetailsRouter {
    override fun navigateTo() {
        router.push(detailsRoute.navigate(context = UUID.randomUUID().toString()))
    }

    override fun dismiss() {
        router.dismiss()
    }
}

