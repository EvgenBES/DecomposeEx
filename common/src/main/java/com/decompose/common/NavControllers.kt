package com.decompose.common

import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.StackNavigation

class NavControllers<C : Any, S : Any>(
    val childNavigation: StackNavigation<C>,
    val slotNavigation: SlotNavigation<S>,
)