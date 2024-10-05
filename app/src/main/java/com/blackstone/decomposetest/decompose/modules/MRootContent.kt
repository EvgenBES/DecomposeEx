package com.blackstone.decomposetest.decompose.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation

@Composable
fun MRootContent(component: MRootComponent) {
    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide() + scale()),
    ) {
        it.instance.componentContent().Content(Modifier)
    }
}
