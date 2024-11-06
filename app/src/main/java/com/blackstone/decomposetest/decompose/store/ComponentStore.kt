package com.blackstone.decomposetest.decompose.store

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle

interface ComponentStore {
    fun setStoreResultListener(
        requestKey: String,
        lifecycle: Lifecycle,
        listener: ComponentResultListener,
    )

    fun setStoreResult(requestKey: String, result: String)
}

inline fun <reified R : Any> ComponentStore.setStoreResultListener(
    requestKey: String,
    crossinline listener: (result: R) -> Unit,
) {
    val lifecycle = (this as? ComponentContext)?.lifecycle
    if (lifecycle != null) {
        setStoreResultListener(requestKey, lifecycle) { result ->
            runCatching {
                listener.invoke(result.deserialize<R>())
            }.also {
                (result as? R)?.let(listener)

                if (it.isFailure) {
                    Log.e("ComponentStore", "SetStoreResultListener error ${it.exceptionOrNull()?.message}")
                }
            }
        }
    }
}

inline fun <reified R : Any> ComponentStore.setStoreResult(
    requestKey: String,
    result: R,
) {
    runCatching {
        setStoreResult(requestKey, result.serialize())
    }.also {
        if (it.isFailure) {
            Log.e("ComponentStore","setStoreResult error ${it.exceptionOrNull()?.message}")
        }
    }
}