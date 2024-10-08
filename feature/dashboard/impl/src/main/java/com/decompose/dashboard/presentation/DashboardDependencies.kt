package com.decompose.dashboard.presentation

import com.decompose.dashboard.api.NavTab
import com.decompose.host.SerializerProvider
import org.koin.core.annotation.Factory

@Factory
class DashboardDependencies(
    val navTabs: List<NavTab>,
    val serializerProvider: SerializerProvider,
)
