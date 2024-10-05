package com.blackstone.decomposetest.decompose.koin

import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCArgs
import kotlinx.serialization.Serializable

@Serializable
sealed interface KRootConfig {

    @Serializable
    sealed interface Child: KRootConfig {

        @Serializable
        data object FeatureA: Child

        @Serializable
        data object FeatureB: Child

        @Serializable
        data class FeatureC(val args: FeatureCArgs): Child
    }
}