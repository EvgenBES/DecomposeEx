package com.decompose.host

import com.decompose.routing.Destination
import kotlinx.serialization.KSerializer

interface SerializerProvider {
    val serializer: KSerializer<Destination>
}