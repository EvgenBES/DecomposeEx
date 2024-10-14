package com.decompose.tab2.presentation.profile.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentScreen
import com.decompose.routing.Destination
import com.decompose.tab2.presentation.profile.screen.ProfileComponent
import com.decompose.tab2.presentation.profile.screen.ProfileScreen
import kotlinx.serialization.Serializable
import org.koin.core.component.get

@Serializable
internal class ProfileDestination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return ProfileScreenProvider(
            componentFactory = componentFactory,
            componentContext = componentContext
        )
    }
}

private class ProfileScreenProvider(
    componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentChild {
    override val screen: ComponentScreen = ProfileScreen(
        ProfileComponent(
            router = componentFactory.get(),
            componentContext = componentContext
        )
    )
}