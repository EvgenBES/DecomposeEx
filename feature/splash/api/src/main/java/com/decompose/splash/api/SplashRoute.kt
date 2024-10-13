package com.decompose.splash.api

import com.decompose.routing.Destination

interface SplashRoute {
    fun destination(): Destination
}
