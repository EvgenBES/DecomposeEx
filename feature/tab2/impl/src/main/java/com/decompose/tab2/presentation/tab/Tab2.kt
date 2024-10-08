package com.decompose.tab2.presentation.tab

import com.decompose.dashboard.api.NavTab
import com.decompose.dashboard.api.NavTabItem
import com.decompose.details.impl.R
import com.decompose.navigation.Destination
import com.decompose.tab2.presentation.routing.Tab2Destination
import org.koin.core.annotation.Factory

private const val TabPosition = 1

@Factory
internal class Tab2 : NavTab {
    override val position: Int = TabPosition
    override val destination: Destination = Tab2Destination()
    override val item: NavTabItem = Tab2Item()
    override val isAvailable: Boolean = true
}

private class Tab2Item : NavTabItem {
    override val unselectedIconId: Int = R.drawable.ic_tab_transfer_unselection
    override val selectedIconId: Int = R.drawable.ic_tab_transfer_selection
    override val titleId: Int = R.string.tab2_title
}

