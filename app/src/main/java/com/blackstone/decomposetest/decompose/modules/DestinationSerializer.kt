package com.blackstone.decomposetest.decompose.modules

import com.arkivanov.essenty.statekeeper.polymorphicSerializer
import com.decompose.cards.presentation.routing.CardsDestination
import com.decompose.details.presentation.routing.DetailsDestination
import com.decompose.navigation.Destination
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalSerializationApi::class)
object DestinationSerializer : KSerializer<Destination> by polymorphicSerializer(
    SerializersModule {
        polymorphic(Destination::class) {
            subclass(DetailsDestination::class, DetailsDestination.serializer())
            subclass(CardsDestination::class, CardsDestination.serializer())
        }
    }
)
