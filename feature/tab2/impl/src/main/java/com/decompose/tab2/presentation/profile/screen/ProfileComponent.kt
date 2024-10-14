package com.decompose.tab2.presentation.profile.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.tab2.presentation.profile.routing.ProfileRouter

internal class ProfileComponent(
    private val router: ProfileRouter,
    componentContext: ComponentContext,
) : ComponentContext by componentContext, ProfileRouter by router