package com.decompose.host

import com.decompose.navigation.Destination
import kotlinx.serialization.KSerializer

interface SerializerProvider {
    val serializer: KSerializer<Destination>
}