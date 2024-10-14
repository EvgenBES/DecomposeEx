package com.decompose.details.navigation

import com.decompose.details.presentation.routing.DetailsDestination
import com.decompose.routing.Destination
import kotlinx.serialization.modules.PolymorphicModuleBuilder

fun PolymorphicModuleBuilder<Destination>.detailsModule() {
    subclass(DetailsDestination::class, DetailsDestination.serializer())
}