package com.decompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ComponentContent {

    @Composable
    fun Content(modifier: Modifier)
}