package com.decompose.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.module.Module

/**
 * Used to create Decompose components. Creation of components are implemented as extension functions.
 */
class ComponentFactory(private val localKoin: Koin) : KoinComponent {
    override fun getKoin(): Koin = localKoin

    fun loadModule(module: Module) {
        getKoin().loadModules(listOf(module))
    }

    fun unloadModule(module: Module) {
        getKoin().unloadModules(listOf(module))
    }
}
