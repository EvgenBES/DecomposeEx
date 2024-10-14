package com.decompose.tab2.presentation.profileLevel2.routing

import com.decompose.tab2.api.Tab2Router
import org.koin.core.annotation.Factory

internal interface ProfileLevel2Router {
    fun navigateTo(index: Int)
}

@Factory
internal class ProfileLevel2RouterImpl(
    private val router: Tab2Router,
    private val profileLevel2: ProfileLevel2Route,
) : ProfileLevel2Router {
    override fun navigateTo(index: Int) {
        router.push(profileLevel2.destination(index = index))
    }
}

