package com.decompose.dashboard.presentation

import com.decompose.dashboard.api.DashboardNavTab
import com.decompose.host.SerializerProvider
import org.koin.core.annotation.Factory

@Factory
class DashboardDependencies(
    val navTabs: List<DashboardNavTab>,
    val serializerProvider: SerializerProvider,
)
