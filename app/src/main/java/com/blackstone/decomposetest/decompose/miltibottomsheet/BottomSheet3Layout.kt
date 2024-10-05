package com.blackstone.decomposetest.decompose.miltibottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet3Layout(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    content: @Composable ColumnScope.() -> Unit,
) {

    LaunchedEffect(Unit) {
        sheetState.show()
    }

    ModalBottomSheet(
        modifier = modifier
            .imePadding()
            .padding(top = VerticalPadding),
        sheetState = sheetState,
        windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        onDismissRequest = onDismiss,
        content = {
            Column(Modifier.padding(bottom = VerticalPadding)) {
                content()
            }
        }
    )

    BackHandler {

    }
}

private val VerticalPadding = 20.dp
