package com.decompose.dashboard.presentation.routing

import com.arkivanov.decompose.ComponentContext
import com.decompose.dashboard.api.DashboardRoute
import com.decompose.dashboard.presentation.DashboardComponent
import com.decompose.dashboard.presentation.DashboardContent
import com.decompose.di.ComponentFactory
import com.decompose.navigation.ComponentChild
import com.decompose.navigation.ComponentContent
import com.decompose.routing.Destination
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.get

@Factory
internal class DashboardImpl : DashboardRoute {
    override fun destination(): Destination {
        return DashboardDestination()
    }
}

@Serializable
class DashboardDestination : Destination {
    override fun factory(
        componentContext: ComponentContext,
        componentFactory: ComponentFactory
    ): ComponentChild {
        return DashboardContentProvider(
            componentFactory = componentFactory,
            componentContext = componentContext
        )
    }
}

private class DashboardContentProvider(
    componentFactory: ComponentFactory,
    componentContext: ComponentContext
) : ComponentChild {
    override val content: ComponentContent = DashboardContent(
        DashboardComponent(
            dependencies = componentFactory.get(),
            componentFactory = componentFactory,
            componentContext = componentContext,
        )
    )
}