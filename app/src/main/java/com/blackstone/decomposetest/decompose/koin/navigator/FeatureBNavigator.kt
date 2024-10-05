package com.blackstone.decomposetest.decompose.koin.navigator

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.blackstone.decomposetest.decompose.koin.KRootConfig
import com.blackstone.decomposetest.decompose.koin.featureB.FeatureBNavigator
import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCArgs
import org.koin.core.annotation.Factory

@Factory
fun featureBNavigatorFactory(childNavigation: StackNavigation<KRootConfig.Child>) = object : FeatureBNavigator {
    override fun navigateBack() {
        childNavigation.pop()
    }

    override fun navigateToFeatureC(userName: String) {
        childNavigation.pushNew(KRootConfig.Child.FeatureC(args = FeatureCArgs(userName)))
    }
}