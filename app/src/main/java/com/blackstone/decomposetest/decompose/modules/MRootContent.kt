package com.blackstone.decomposetest.decompose.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.blackstone.decomposetest.decompose.modules.bottomSheet.rememberSlotModalBottomSheetState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MRootContent(component: MRootComponent) {

    val bottomSheetState = rememberSlotModalBottomSheetState(
        component.slot,
        onDismiss = component::dismiss
    ) {
        it.instance.content.Content(Modifier)
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState.sheetState,
        sheetContent = bottomSheetState.sheetContent.value
    ) {
        Children(
            stack = component.stack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(slide() + scale()),
        ) {
            it.instance.content.Content(Modifier)
        }
    }

}
