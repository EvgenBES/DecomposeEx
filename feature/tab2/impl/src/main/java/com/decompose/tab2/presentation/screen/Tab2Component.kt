package com.decompose.tab2.presentation.screen

import com.arkivanov.decompose.ComponentContext
import com.decompose.tab2.presentation.routing.Tab2Router

internal class Tab2Component(
    private val router: Tab2Router,
    componentContext: ComponentContext
) : ComponentContext by componentContext, Tab2Router by router