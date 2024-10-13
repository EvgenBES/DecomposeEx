package com.decompose.details.api

import com.decompose.routing.Destination

interface DetailsRoute {
    fun navigate(context: String): Destination
}
