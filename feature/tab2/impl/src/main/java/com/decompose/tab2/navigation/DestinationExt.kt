package com.decompose.tab2.navigation

import com.decompose.routing.Destination
import com.decompose.tab2.presentation.host.Tab2Destination
import com.decompose.tab2.presentation.profile.routing.ProfileDestination
import com.decompose.tab2.presentation.profileLevel2.routing.ProfileLevel2Destination
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun PolymorphicModuleBuilder<Destination>.profileModule() {
    subclass(Tab2Destination::class, Tab2Destination.serializer())
    subclass(ProfileDestination::class, ProfileDestination.serializer())
    subclass(ProfileLevel2Destination::class, ProfileLevel2Destination.serializer())
}