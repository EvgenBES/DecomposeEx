package com.blackstone.decomposetest.decompose.store

import android.os.Bundle
import android.os.Parcelable
import com.arkivanov.essenty.lifecycle.Lifecycle
import java.util.Collections

class ComponentStoreImpl : ComponentStore {

    private val mResults: MutableMap<String, String> = Collections.synchronizedMap(HashMap())
    private val mResultListeners: MutableMap<String, ComponentResultListener> = Collections.synchronizedMap(HashMap())

    override fun setStoreResultListener(
        requestKey: String,
        lifecycle: Lifecycle,
        listener: ComponentResultListener,
    ) {
        if (lifecycle.state == Lifecycle.State.DESTROYED) return

        val observer = object : Lifecycle.Callbacks {
            override fun onStart() {
                super.onStart()
                val storedResult = mResults[requestKey]
                if (storedResult != null) {
                    listener.onResult(storedResult)
                    clearResult(requestKey)
                }
            }

            override fun onDestroy() {
                super.onDestroy()
                lifecycle.unsubscribe(this)
                clearResultListener(requestKey)
            }
        }

        lifecycle.subscribe(observer)
        setComponentResultListener(requestKey, listener)
    }

    override fun setStoreResult(requestKey: String, result: String) {
        val resultListener: ComponentResultListener? = mResultListeners[requestKey]
        // if there is and it is started, fire the callback
        if (resultListener != null) {
            resultListener.onResult(result)
        } else {
            // else, save the result for later
            mResults[requestKey] = result
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
            bundle.putString(RESULT_KEY_PREFIX + resultName, mResults[resultName].orEmpty())
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

    companion object {
        private const val RESULT_KEY_PREFIX: String = "result_"
        const val SAVED_STATE_TAG: String = "app:component:store"
    }
}

