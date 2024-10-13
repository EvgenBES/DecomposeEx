package com.decompose.routing

import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.decompose.common.NavControllers

class DefaultRouter(private val navControllers: NavControllers<Destination, Destination>): Router {
    override fun push(destination: Destination) {
        navControllers.childNavigation.pushNew(destination)
    }

    override fun replace(destination: Destination) {
        navControllers.childNavigation.replaceCurrent(destination)
    }

    override fun replaceAll(destinations: List<Destination>) {
        if (destinations.isEmpty()) return
        navControllers.childNavigation.replaceAll(*destinations.toTypedArray())
    }

    override fun activate(destination: Destination) {
        navControllers.slotNavigation.activate(destination)
    }

    override fun back() {
       navControllers.childNavigation.pop()
    }

    override fun dismiss() {
        navControllers.slotNavigation.dismiss()
    }
}