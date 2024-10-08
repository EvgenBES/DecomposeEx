package com.decompose.dashboard.api

import com.decompose.navigation.Destination

interface DashboardRoute {
    fun destination(): Destination
}