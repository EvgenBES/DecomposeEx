package com.decompose.tab2.presentation.host

import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.decompose.routing.DefaultRouter
import com.decompose.routing.Destination
import com.decompose.routing.Router
import com.decompose.tab2.api.Tab2Router

internal class Tab2RouterImpl(
    private val childNavigation: StackNavigation<Destination>,
    private val slotNavigation: SlotNavigation<Destination>
) : Tab2Router, Router by DefaultRouter(childNavigation, slotNavigation)