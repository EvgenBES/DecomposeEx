package com.decompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ComponentScreen {

    @Composable
    fun Content(modifier: Modifier)
}