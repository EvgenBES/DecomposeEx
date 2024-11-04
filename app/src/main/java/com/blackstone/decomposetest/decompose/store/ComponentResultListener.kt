package com.blackstone.decomposetest.decompose.store

fun interface ComponentResultListener {
    /**
     * Callback used to handle results passed between fragments.
     * @param result result passed to the callback
     */
    fun onResult(result: ResultContainer<*>)
}
