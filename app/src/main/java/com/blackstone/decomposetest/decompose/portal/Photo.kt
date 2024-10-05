package com.blackstone.decomposetest.decompose.portal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext

interface PhotoComponent

class DefaultPhotoComponent(
    componentContext: ComponentContext,
    val photoId: String,
) : PhotoComponent, ComponentContext by componentContext

@Composable
fun PhotoContent(component: PhotoComponent) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Photo screen")
    }
}