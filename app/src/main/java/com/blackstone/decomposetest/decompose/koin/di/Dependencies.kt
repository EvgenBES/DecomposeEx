package com.blackstone.decomposetest.decompose.koin.di

import org.koin.core.annotation.Factory

@Factory
class Repository {
    fun fetchUserName() = "User Unknown"
}

@Factory
class UseCase1

@Factory
class UseCase2

@Factory
class UseCase3 {
    fun load() = Unit
}