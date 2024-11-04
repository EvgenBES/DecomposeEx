package com.blackstone.decomposetest.decompose.store

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.serialization.Serializable

@Serializable
data class ResultContainer<T>(val value: T)

interface ComponentStore {
    fun <T> setStoreResultListener(
        requestKey: String,
        lifecycle: Lifecycle,
        listener: (result: ResultContainer<T>) -> Unit,
    )

    fun <T> setStoreResult(requestKey: String, result: ResultContainer<T>)
}

fun <T>ComponentStore.setStoreResultListener(
    requestKey: String,
    listener: (result: ResultContainer<T>) -> Unit,
) {
    val lifecycle = (this as? ComponentContext)?.lifecycle
    if (lifecycle != null) {
        setStoreResultListener(requestKey, lifecycle) { result ->
            listener(result)
        }
    }
}