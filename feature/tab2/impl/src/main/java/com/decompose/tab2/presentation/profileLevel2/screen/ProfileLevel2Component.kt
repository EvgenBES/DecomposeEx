package com.decompose.tab2.presentation.profileLevel2.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.tab2.presentation.profileLevel2.routing.ProfileLevel2Router

internal class ProfileLevel2Component(
    val index: Int,
    private val router: ProfileLevel2Router,
    componentContext: ComponentContext,
) : ComponentContext by componentContext, ProfileLevel2Router by router {

    fun navigateToNext() {
        router.navigateTo(index + 1)
    }
}