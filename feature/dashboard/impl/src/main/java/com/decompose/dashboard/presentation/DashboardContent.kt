package com.decompose.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.util.fastForEach
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.decompose.dashboard.api.NavTab
import com.decompose.dashboard.api.NavTabItem
import com.decompose.navigation.ComponentContent

class DashboardContent(
    private val component: DashboardComponent
) : ComponentContent {
    @Composable
    override fun Content(modifier: Modifier) {
        val childStack by component.stack.subscribeAsState()
        val activeConfig = childStack.active.configuration

        val tabs = remember { component.fetchTabs() }
        val activeTab: NavTabItem = remember(activeConfig) {
            tabs.find { it.destination == activeConfig }?.item
                ?: error("Unknown active config $activeConfig")
        }

        Column(Modifier.fillMaxSize()) {
            Children(
                modifier = Modifier.weight(1f),
                stack = childStack,
                animation = stackAnimation(slide())
            ) {
                it.instance.content.Content(Modifier)
            }

            BottomMenu(
                tabs = tabs,
                activeTab = activeTab,
                onTabClick = remember { { component.onTabClicked(it) } }
            )
        }
    }
}

@Composable
private fun BottomMenu(
    tabs: List<NavTab>,
    activeTab: NavTabItem,
    onTabClick: (NavTab) -> Unit,
) {
    NavigationBar {
        Row{
            tabs.fastForEach { navMenu ->
                val tab = navMenu.item
                val selected = activeTab == tab
                val icon = if (selected) tab.selectedIconId else tab.unselectedIconId

                NavigationBarItem(
                    selected = selected,
                    onClick = { onTabClick(navMenu) },
                    icon = {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = stringResource(id = tab.titleId),
                        )
                    },
                    label = {
                        Text(text = stringResource(id = tab.titleId))
                    },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                )
            }
        }
    }
}