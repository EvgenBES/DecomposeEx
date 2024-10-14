package com.decompose.splash.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.decompose.navigation.ComponentScreen

internal class SplashScreen(
    private val component: SplashComponent
) : ComponentScreen {
    @Composable
    override fun Content(modifier: Modifier) {

        SplashView(
            modifier = modifier,
            onPush = component::navigateToPush,
            onReplace = component::navigateToReplace,
            onReplaceAll = component::navigateToReplaceAll,
            onReplaceAllEmpty = component::navigateToReplaceAllEmpty,
        )
    }
}

@Composable
private fun SplashView(
    modifier: Modifier,
    onPush: () -> Unit = {},
    onReplace: () -> Unit = {},
    onReplaceAll: () -> Unit = {},
    onReplaceAllEmpty: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {

                Button(onClick = onPush) {
                    Text("to Dashboard - push")
                }

                Button(onClick = onReplace) {
                    Text("to Dashboard - replace")
                }

                Button(onClick = onReplaceAll) {
                    Text("to Dashboard - replaceAll")
                }

                Button(onClick = onReplaceAllEmpty) {
                    Text("to Dashboard - onReplaceAllEmpty")
                }
            }
        }
    }
}
