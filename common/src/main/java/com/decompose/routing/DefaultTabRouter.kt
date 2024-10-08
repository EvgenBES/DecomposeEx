package com.decompose.routing

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.decompose.navigation.Destination

class DefaultTabRouter(
    private val childNavigation: StackNavigation<Destination>
) : TabRouter {
    override fun push(destination: Destination) {
        childNavigation.bringToFront(destination)
    }

    override fun replace(destination: Destination) {
        childNavigation.replaceCurrent(destination)
    }

    override fun replaceAll(destinations: List<Destination>) {
        if (destinations.isEmpty()) return
        childNavigation.replaceAll(*destinations.toTypedArray())
    }

    override fun back() {
        childNavigation.pop()
    }
}