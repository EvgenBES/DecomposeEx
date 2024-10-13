package com.blackstone.decomposetest.decompose.modules

import com.arkivanov.essenty.statekeeper.polymorphicSerializer
import com.decompose.cards.presentation.routing.CardsDestination
import com.decompose.dashboard.presentation.routing.DashboardDestination
import com.decompose.details.presentation.routing.DetailsDestination
import com.decompose.host.SerializerProvider
import com.decompose.routing.Destination
import com.decompose.splash.presentation.routing.SplashDestination
import com.decompose.tab1.presentation.routing.Tab1Destination
import com.decompose.tab2.presentation.routing.Tab2Destination
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.core.annotation.Singleton


@OptIn(ExperimentalSerializationApi::class)
object DestinationSerializer : KSerializer<Destination> by polymorphicSerializer(
    SerializersModule {
        polymorphic(Destination::class) {
            subclass(SplashDestination::class, SplashDestination.serializer())
            subclass(DetailsDestination::class, DetailsDestination.serializer())
            subclass(CardsDestination::class, CardsDestination.serializer())
            subclass(DashboardDestination::class, DashboardDestination.serializer())
            subclass(Tab1Destination::class, Tab1Destination.serializer())
            subclass(Tab2Destination::class, Tab2Destination.serializer())
        }
    }
)

@Singleton
internal class SerializerProviderImpl(
    override val serializer: KSerializer<Destination> = DestinationSerializer
) : SerializerProvider