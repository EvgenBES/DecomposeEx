package com.decompose.routing

import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent

class DefaultRouter(
    private val childNavigation: StackNavigation<Destination>,
    private val slotNavigation: SlotNavigation<Destination>,
) : Router {
    override fun push(destination: Destination) {
        childNavigation.pushNew(destination)
    }

    override fun replace(destination: Destination) {
        childNavigation.replaceCurrent(destination)
    }

    override fun replaceAll(destinations: List<Destination>) {
        if (destinations.isEmpty()) return
        childNavigation.replaceAll(*destinations.toTypedArray())
    }

    override fun activate(destination: Destination) {
        slotNavigation.activate(destination)
    }

    override fun back() {
        childNavigation.pop()
    }

    override fun dismiss() {
        slotNavigation.dismiss()
    }
}