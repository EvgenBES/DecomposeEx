package com.decompose.host

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.decompose.navigation.ComponentChild

interface HostComponent {
    val stack: Value<ChildStack<*, ComponentChild>>
    val slot: Value<ChildSlot<*, ComponentChild>>
    fun dismiss()
}