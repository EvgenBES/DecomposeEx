package com.decompose.navigation

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory

interface Destination {
    fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild
}