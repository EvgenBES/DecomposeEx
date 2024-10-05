package com.blackstone.decomposetest.decompose.portal


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable

interface ChatComponent {
    fun onPhotoClicked(id: String)
}

class DefaultChatComponent(
    componentContext: ComponentContext,
    private val portal: ChatPortal,
) : ChatComponent, ComponentContext by componentContext {

    override fun onPhotoClicked(id: String) {
        portal.show(ChatPortal.Config.Photo(id = id))
    }
}

class ChatPortal(
    val show: (Config) -> Unit,
) {

    fun create(config: Config, context: ComponentContext): Child =
        when (config) {
            is Config.Photo -> Child.PhotoChild(DefaultPhotoComponent(componentContext = context, photoId = config.id))
        }

    @Serializable
    sealed class Config {
        data class Photo(val id: String) : Config()
    }

    sealed class Child {
        class PhotoChild(val component: PhotoComponent) : Child()
    }
}

@Composable
fun ChatContent(component: ChatComponent) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { component.onPhotoClicked(id = "someId") },
        ) {
            Text("Show photo")
        }
    }
}

@Composable
fun ChatPortalContent(child: ChatPortal.Child) {
    when (child) {
        is ChatPortal.Child.PhotoChild -> PhotoContent(child.component)
    }
}