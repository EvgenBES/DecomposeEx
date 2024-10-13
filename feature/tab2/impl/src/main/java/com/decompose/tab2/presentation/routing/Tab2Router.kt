package com.decompose.tab2.presentation.routing

import com.decompose.details.api.DetailsRoute
import com.decompose.routing.Router
import org.koin.core.annotation.Factory
import java.util.UUID

internal interface Tab2Router {
    fun navigateTo()
}

@Factory
internal class Tab2RouterImpl(
    private val router: Router,
    private val detailsRoute: DetailsRoute
) : Tab2Router {
    override fun navigateTo() {
        router.push(detailsRoute.navigate(context = UUID.randomUUID().toString()))
    }
}

