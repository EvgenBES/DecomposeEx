package com.blackstone.decomposetest.decompose.portal

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
import kotlinx.serialization.Serializable

interface PortalRootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class ChatChild(val component: ChatComponent) : Child()
        class ChatPortalChild(val child: ChatPortal.Child) : Child()
    }
}

class DefaultPortalRootComponent(
    componentContext: ComponentContext,
) : PortalRootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    private val chatPortal = ChatPortal { nav.push(Config.ChatChild(it)) }

    override val stack: Value<ChildStack<*, PortalRootComponent.Child>> = childStack(
        source = nav,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.Chat) },
        handleBackButton = true,
    ) { config, childContext ->
        when (config) {
            is Config.Chat -> PortalRootComponent.Child.ChatChild(DefaultChatComponent(childContext, chatPortal))
            is Config.ChatChild -> PortalRootComponent.Child.ChatPortalChild(chatPortal.create(config.config, childContext))
        }
    }

    @Serializable
    private sealed class Config {
        @Serializable
        data object Chat : Config()

        @Serializable
        data class ChatChild(val config: ChatPortal.Config) : Config()
    }
}

@Composable
fun RootContent(component: PortalRootComponent) {
    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide() + scale()),
    ) {
        when (val child = it.instance) {
            is PortalRootComponent.Child.ChatChild -> ChatContent(child.component)
            is PortalRootComponent.Child.ChatPortalChild -> ChatPortalContent(child.child)
        }
    }
}