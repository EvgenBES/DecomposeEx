package com.blackstone.decomposetest.decompose.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.blackstone.decomposetest.decompose.bottomsheet3.BottomSheet3Layout
import com.blackstone.decomposetest.decompose.bottomsheet3.rememberSlotModalBottomSheet3State

@Composable
fun MRootContent(component: MRootComponent) {

    val bottomSheetState = rememberSlotModalBottomSheet3State(
        slot = component.slot,
    ) {
        it.instance.screen.Content(Modifier)
    }

    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide()),
    ) {
        it.instance.screen.Content(Modifier)
    }

    BottomSheet3Layout(
        content = bottomSheetState.sheetContent.value,
        isVisible = bottomSheetState.isVisible.value,
        dragHandle = null,
        onDismiss = remember { { component.dismiss() } }
    )
}
