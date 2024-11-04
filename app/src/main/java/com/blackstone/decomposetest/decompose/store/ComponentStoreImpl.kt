package com.blackstone.decomposetest.decompose.store

import android.os.Bundle
import android.os.Parcelable
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Collections

class ComponentStoreImpl : ComponentStore {
    private val json: Json by lazy {
        Json {
            isLenient = true
            allowStructuredMapKeys = true
            ignoreUnknownKeys = true
        }
    }

    private val mResults: MutableMap<String, String> = Collections.synchronizedMap(HashMap())
    private val mResultListeners: MutableMap<String, ComponentResultListener> = Collections.synchronizedMap(HashMap())

    @Suppress("UNCHECKED_CAST")
    override fun <T> setStoreResultListener(
        requestKey: String,
        lifecycle: Lifecycle,
        listener: (result: ResultContainer<T>) -> Unit,
    ) {
        // once we are started, check for any stored results
        val storedResult = mResults[requestKey]
        if (storedResult != null) {
            // if there is a result, fire the callback
            listener(storedResult.deserialize())
            // and clear the result
            clearResult(requestKey)
        }

        setComponentResultListener(requestKey, listener as (ResultContainer<*>) -> Unit)
    }

    override fun <T> setStoreResult(requestKey: String, result: ResultContainer<T>) {
        val resultListener: ComponentResultListener? = mResultListeners[requestKey]
        // if there is and it is started, fire the callback
        if (resultListener != null) {
            resultListener.onResult(result)
        } else {
            // else, save the result for later
            mResults[requestKey] = result.serialize()
        }
    }

    fun clearResultListener(requestKey: String) {
        mResultListeners.remove(requestKey)
    }

    fun saveAllState(): Parcelable? {
        val savedState: Bundle = saveAllStateInternal()
        return if (savedState.isEmpty) null else savedState
    }

    private fun saveAllStateInternal(): Bundle {
        val bundle = Bundle()

        for (resultName in mResults.keys) {
            bundle.putString(RESULT_KEY_PREFIX + resultName, mResults[resultName]?.serialize().orEmpty())
        }

        return bundle
    }

    fun restoreSaveState(state: Parcelable?) {
        // If there is no saved state at all, then there's nothing else to do
        if (state == null) return
        val bundle = state as Bundle

        // Restore the component results
        for (bundleKey in bundle.keySet()) {
            if (bundleKey.startsWith(RESULT_KEY_PREFIX)) {
                val savedResult = bundle.getString(bundleKey)
                if (savedResult != null) {
                    val resultKey: String = bundleKey.substring(RESULT_KEY_PREFIX.length)
                    mResults[resultKey] = savedResult
                }
            }
        }
    }

    private fun setComponentResultListener(
        requestKey: String,
        listener: ComponentResultListener,
    ) {
        mResultListeners[requestKey] = listener
    }

    private fun clearResult(requestKey: String) {
        mResults.remove(requestKey)
    }

    private inline fun <reified T : Any> T.serialize(): String {
        return json.encodeToString(this)
    }

    private inline fun <reified T : Any> String.deserialize(): T {
        return json.decodeFromString(this)
    }

    companion object {
        private const val RESULT_KEY_PREFIX: String = "result_"
        const val SAVED_STATE_TAG: String = "app:component:store"
    }
}

