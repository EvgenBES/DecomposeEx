package com.blackstone.decomposetest.decompose.koin.featureB

import com.blackstone.decomposetest.decompose.koin.di.Repository
import com.blackstone.decomposetest.decompose.koin.di.UseCase2
import org.koin.core.annotation.Factory

@Factory
class FeatureBDependencies(
    val repository: Repository,
    val useCase2: UseCase2,
)