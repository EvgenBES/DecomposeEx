package com.decompose.splash.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentContent
import com.decompose.navigation.Destination
import com.decompose.splash.api.SplashRoute
import com.decompose.splash.presentation.screen.SplashComponent
import com.decompose.splash.presentation.screen.SplashContent
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class SplashRouteImpl : SplashRoute {
    override fun destination(): Destination {
        return SplashDestination()
    }
}

@Serializable
class SplashDestination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return SplashContentProvider(
            router = componentFactory.get(),
            componentContext = componentContext
        )
    }
}

private class SplashContentProvider(
    private val router: SplashRouter,
    componentContext: ComponentContext
) : ComponentChild {
    override val content: ComponentContent = SplashContent(
        SplashComponent(
            router = router,
            componentContext = componentContext
        )
    )
}