package com.blackstone.decomposetest.decompose.miltibottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

@Composable
fun BottomSheetScreen(
    component: BottomSheetComponent
) {
    Box {

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(text = "BottomSheetScreen")

            Button(onClick = component::showBottomSheetOne) {
                Text(text = "Show BS One")
            }
        }

        BottomSheetContent(
            component.stack,
            onDismiss = component::navigateBack,
            onPushConfig = component::pushConfig
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    stack: Value<ChildStack<*, BottomSheetComponent.Child>>,
    onDismiss: () -> Unit,
    onPushConfig: (BottomSheetComponent.BSConfig) -> Unit,
) {
//    val _state = stack.subscribeAsState()
//
//    _state.value.items.forEach {
//        when (val child = it.instance) {
//            is BottomSheetComponent.Child.Child1 -> BottomSheet3Layout(onDismiss = onDismiss) {
//                BSChildScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Red), child.component, text = "BS ONE",
//                    onClick = {
//                        onPushConfig(BottomSheetComponent.BSConfig.BSTwo)
//                    }
//                )
//            }
//
//            is BottomSheetComponent.Child.Child2 -> BottomSheet3Layout(onDismiss = onDismiss) {
//                BSChildScreen(
//                    modifier = Modifier
//                        .fillMaxHeight(0.75f)
//                        .background(Color.Blue), child.component, text = "BS TWO",
//                    onClick = {
//                        onPushConfig(BottomSheetComponent.BSConfig.BSThree)
//                    }
//                )
//            }
//
//            is BottomSheetComponent.Child.Child3 -> BottomSheet3Layout(onDismiss = onDismiss) {
//                BSChildScreen(
//                    modifier = Modifier
//                        .fillMaxHeight(.5f)
//                        .background(Color.Yellow), child.component, text = "BS TREE",
//                    onClick = onDismiss
//                )
//            }
//
//            is BottomSheetComponent.Child.Empty -> Unit
//        }
//    }
}