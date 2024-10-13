package com.decompose.navTab

import com.decompose.routing.Destination

interface NavTab {
    val position: Int
    val destination: Destination
    val item: NavTabItem
}
