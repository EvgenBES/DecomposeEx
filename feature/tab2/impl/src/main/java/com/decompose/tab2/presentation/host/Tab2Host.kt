package com.decompose.tab2.presentation.host

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.decompose.bottomSheet.rememberSlotModalBottomSheetState
import androidx.compose.material.ModalBottomSheetLayout
import com.decompose.host.HostComponent
import com.decompose.navigation.ComponentScreen

@OptIn(ExperimentalMaterialApi::class)
internal class Tab2Host(
    private val component: HostComponent
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {

        val bottomSheetState = rememberSlotModalBottomSheetState(
            slot = component.slot,
            onDismiss = component::dismiss,
            skipHalfExpanded = true,
        ) {
            it.instance.screen.Content(Modifier)
        }

        ModalBottomSheetLayout(
            sheetState = bottomSheetState.sheetState,
            sheetContent = bottomSheetState.sheetContent.value
        ) {
            Children(
                stack = component.stack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(slide()),
            ) {
                it.instance.screen.Content(Modifier)
            }
        }

    }
}

@Composable
private fun TabView(
    modifier: Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text("Tab2")

                Button(onClick = onClick) {
                    Text("Click to In Details")
                }
            }
        }
    }
}
