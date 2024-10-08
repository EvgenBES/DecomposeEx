package com.decompose.dashboard.api

import com.decompose.navigation.Destination

interface NavTab {
    val position: Int
    val destination: Destination
    val item: NavTabItem
    val isAvailable: Boolean
}
