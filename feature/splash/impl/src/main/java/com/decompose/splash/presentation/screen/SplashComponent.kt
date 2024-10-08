package com.decompose.splash.presentation.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.splash.presentation.routing.SplashRouter

internal class SplashComponent(
    private val router: SplashRouter,
    componentContext: ComponentContext
) : ComponentContext by componentContext, SplashRouter by router