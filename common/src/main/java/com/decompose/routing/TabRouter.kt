package com.decompose.routing

import com.decompose.navigation.Destination

interface TabRouter {
    fun push(destination: Destination)
    fun replace(destination: Destination)
    fun replaceAll(destinations: List<Destination>)
    fun back()
}
