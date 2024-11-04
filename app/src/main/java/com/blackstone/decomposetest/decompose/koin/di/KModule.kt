package com.blackstone.decomposetest.decompose.koin.di

import com.blackstone.decomposetest.decompose.store.ComponentStore
import com.blackstone.decomposetest.decompose.store.ComponentStoreImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

@Module
@ComponentScan("com.blackstone.decomposetest.decompose")
class KModule {

    @Singleton
    fun provideComponentStoreImpl(): ComponentStoreImpl {
        return ComponentStoreImpl()
    }

    @Singleton
    fun provideComponentStore(impl: ComponentStoreImpl): ComponentStore {
        return impl
    }
}