package com.blackstone.decomposetest.decompose.miltibottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext

class BSChildComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext


@Composable
fun BSChildScreen(
    modifier: Modifier,
    component: BSChildComponent,
    text: String,
    onClick: () -> Unit
) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = text)

        Button(onClick = onClick, modifier = Modifier.padding(top = 56.dp)) {
            Text(text = "ADD +1")
        }
    }
}