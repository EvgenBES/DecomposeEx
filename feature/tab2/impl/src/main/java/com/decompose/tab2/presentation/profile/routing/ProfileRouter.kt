package com.decompose.tab2.presentation.profile.routing

import com.decompose.tab2.api.Tab2Router
import com.decompose.tab2.presentation.profileLevel2.routing.ProfileLevel2Route
import org.koin.core.annotation.Factory

internal interface ProfileRouter {
    fun navigateTo()
}

@Factory
internal class ProfileRouterImpl(
    private val router: Tab2Router,
    private val profileLevel2Route: ProfileLevel2Route
) : ProfileRouter {
    override fun navigateTo() {
        router.push(profileLevel2Route.destination(index = 1))
    }
}

