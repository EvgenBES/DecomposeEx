package com.decompose.dashboard.api

import com.decompose.routing.Destination

interface DashboardRoute {
    fun destination(): Destination
}