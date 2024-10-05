package com.blackstone.decomposetest.decompose.restore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.blackstone.decomposetest.decompose.restore.second.SecondComponent
import com.blackstone.decomposetest.decompose.restore.second.SecondScreen
import com.blackstone.decomposetest.decompose.restore.start.StartScreen
import kotlinx.serialization.Serializable

class RestoreComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val nav = StackNavigation<RestoreConfig>()

    val stack: Value<ChildStack<*, Child>> = childStack(
        source = nav,
        serializer = RestoreConfig.serializer(),
        initialStack = { listOf(RestoreConfig.Start) },
        handleBackButton = true,
    ) { config, childContext ->
        when (config) {
            RestoreConfig.Start -> Child.Start(childContext)
            RestoreConfig.Second -> Child.Second(SecondComponent(childContext))
        }
    }

    fun next() {
        nav.push(RestoreConfig.Second)
    }


    @Serializable
    sealed class RestoreConfig {
        @Serializable
        data object Start : RestoreConfig()

        @Serializable
        data object Second : RestoreConfig()
    }

    sealed class Child {
        class Start(val component: ComponentContext) : Child()
        class Second(val component: SecondComponent) : Child()
    }
}

@Composable
fun RestoreScreen(component: RestoreComponent) {

    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide() + scale()),
    ) {
        when (val child = it.instance) {
            is RestoreComponent.Child.Start -> StartScreen(component::next)
            is RestoreComponent.Child.Second -> SecondScreen(child.component)
        }
    }
}