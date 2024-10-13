package com.decompose.routing

interface Router {
    fun push(destination: Destination)
    fun replace(destination: Destination)
    fun replaceAll(destinations: List<Destination>)

    fun activate(destination: Destination)

    fun back()
    fun dismiss()
}


