package com.decompose.tab2.presentation.profileLevel2.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import com.decompose.tab2.presentation.profileLevel2.screen.ProfileLevel2Component
import com.decompose.tab2.presentation.profileLevel2.screen.ProfileLevel2Screen
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class ProfileLevel2Route {
    fun destination(index: Int): Destination {
        return ProfileLevel2Destination(index)
    }
}

@Serializable
internal class ProfileLevel2Destination(private val index: Int) : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return ProfileLevel2ScreenProvider(
            index = index,
            componentFactory = componentFactory,
            componentContext = componentContext
        )
    }
}

private class ProfileLevel2ScreenProvider(
    index: Int,
    componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = ProfileLevel2Screen(
        ProfileLevel2Component(
            index = index,
            router = componentFactory.get(),
            componentContext = componentContext
        )
    )
}