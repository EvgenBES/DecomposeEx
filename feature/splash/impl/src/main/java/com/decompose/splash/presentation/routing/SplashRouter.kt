package com.decompose.splash.presentation.routing

import com.decompose.dashboard.api.DashboardRoute
import com.decompose.routing.Router
import org.koin.core.annotation.Factory

internal interface SplashRouter {
    fun navigateToPush()
    fun navigateToReplace()
    fun navigateToReplaceAll()
    fun navigateToReplaceAllEmpty()
}

@Factory
internal class SplashRouterImpl(
    private val router: Router,
    private val dashboard: DashboardRoute
) : SplashRouter {
    override fun navigateToPush() {
        router.push(dashboard.destination())
    }

    override fun navigateToReplace() {
        router.replace(dashboard.destination())
    }

    override fun navigateToReplaceAll() {
        router.replaceAll(listOf(dashboard.destination()))
    }

    override fun navigateToReplaceAllEmpty() {
        router.replaceAll(listOf())
    }
}

