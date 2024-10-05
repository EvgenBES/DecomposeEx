package com.decompose.routing

import com.decompose.common.NavControllers
import com.decompose.navigation.Destination

internal class RootRouter(private val navControllers: NavControllers<Destination, Destination>): Router {
    override fun push(destination: Destination) {
        TODO("Not yet implemented")
    }

    override fun replace(destination: Destination) {
        TODO("Not yet implemented")
    }

    override fun replaceAll(destinations: List<Destination>) {
        TODO("Not yet implemented")
    }

    override fun back() {
        TODO("Not yet implemented")
    }

    override fun dismiss() {
        TODO("Not yet implemented")
    }


}