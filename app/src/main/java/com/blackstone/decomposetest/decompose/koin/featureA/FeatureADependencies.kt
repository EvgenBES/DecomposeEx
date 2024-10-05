package com.blackstone.decomposetest.decompose.koin.featureA

import com.blackstone.decomposetest.decompose.koin.di.Repository
import com.blackstone.decomposetest.decompose.koin.di.UseCase1
import org.koin.core.annotation.Factory

@Factory
class FeatureADependencies(
    val repository: Repository,
    val useCase1: UseCase1,
)