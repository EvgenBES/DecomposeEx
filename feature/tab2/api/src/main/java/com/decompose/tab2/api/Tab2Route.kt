package com.decompose.tab2.api

import com.decompose.routing.Destination

interface Tab2Route {
    fun destination(): Destination
}