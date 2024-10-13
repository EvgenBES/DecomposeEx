package com.decompose.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild

interface Destination {
    fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild
}