package com.decompose.details.api

import com.decompose.navigation.Destination

interface DetailsRoute {
    fun navigate(context: String): Destination
}
