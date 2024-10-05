package com.blackstone.decomposetest.decompose.koin.navigator

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pushNew
import com.blackstone.decomposetest.decompose.koin.KRootConfig
import com.blackstone.decomposetest.decompose.koin.featureA.FeatureANavigator
import com.blackstone.decomposetest.decompose.koin.featureC.FeatureCArgs
import org.koin.core.annotation.Factory

@Factory
fun featureANavigatorFactory(childNavigation: StackNavigation<KRootConfig.Child>) = object : FeatureANavigator {
    override fun navigateToFeatureB() {
        childNavigation.pushNew(KRootConfig.Child.FeatureB)
    }

    override fun navigateToFeatureC(userName: String) {
        childNavigation.pushNew(KRootConfig.Child.FeatureC(args = FeatureCArgs(userName)))
    }
}