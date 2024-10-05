package com.blackstone.decomposetest.decompose.restore.second

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    component: SecondComponent
) {
    val state by component.uiState.collectAsState()

    Log.e("AAQQ", "VIEW STATE $state")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = state.inputText1, onValueChange = component::updateText1)

        Spacer(modifier = Modifier.padding(24.dp))

        TextField(value = state.inputText2, onValueChange = component::updateText2)
    }
}