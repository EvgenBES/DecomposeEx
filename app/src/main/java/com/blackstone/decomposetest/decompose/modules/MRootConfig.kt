package com.blackstone.decomposetest.decompose.modules

import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCArgs
import kotlinx.serialization.Serializable

@Serializable
sealed interface MRootConfig {

    @Serializable
    sealed interface Child: MRootConfig {

        @Serializable
        data object FeatureA: Child

        @Serializable
        data object FeatureB: Child

        @Serializable
        data class FeatureC(val args: FeatureCArgs): Child
    }

    @Serializable
    sealed interface Slot: MRootConfig {

        @Serializable
        data object FeatureA: Child

        @Serializable
        data object FeatureB: Child

        @Serializable
        data class FeatureC(val args: FeatureCArgs): Child
    }
}