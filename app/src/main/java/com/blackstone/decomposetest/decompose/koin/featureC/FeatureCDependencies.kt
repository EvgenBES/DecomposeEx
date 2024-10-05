package com.blackstone.decomposetest.decompose.koin.featureC

import com.blackstone.decomposetest.decompose.koin.di.UseCase3
import org.koin.core.annotation.Factory

@Factory
class FeatureCDependencies(
    val useCase3: UseCase3
)