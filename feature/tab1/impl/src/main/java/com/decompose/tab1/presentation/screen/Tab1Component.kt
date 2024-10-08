package com.decompose.tab1.presentation.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.tab1.presentation.routing.Tab1Router

internal class Tab1Component(
    private val router: Tab1Router,
    componentContext: ComponentContext
) : ComponentContext by componentContext, Tab1Router by router