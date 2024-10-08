package com.decompose.tab1.presentation.tab

import com.decompose.dashboard.api.NavTab
import com.decompose.dashboard.api.NavTabItem
import com.decompose.details.impl.R
import com.decompose.navigation.Destination
import com.decompose.tab1.presentation.routing.Tab1Destination
import org.koin.core.annotation.Factory

private const val TabPosition = 0

@Factory
internal class Tab1 : NavTab {
    override val position: Int = TabPosition
    override val destination: Destination = Tab1Destination()
    override val item: NavTabItem = Tab1Item()
    override val isAvailable: Boolean = true
}

private class Tab1Item : NavTabItem {
    override val unselectedIconId: Int = R.drawable.ic_tab_home_unselected
    override val selectedIconId: Int = R.drawable.ic_tab_home_selected
    override val titleId: Int = R.string.home_tab_title
}

