package com.blackstone.decomposetest.decompose.restore.second

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.SerializableContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable

class SecondComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val statefulEntity = instanceKeeper.getOrCreate {
        SomeStatefulEntity(savedState = stateKeeper.consume(key = "SAVED_STATE", strategy = SerializableContainer.serializer()))
    }

    val uiState = statefulEntity.state

    init {
        stateKeeper.register(
            key = "SAVED_STATE",
            strategy = SerializableContainer.serializer(),
            supplier = statefulEntity::saveState,
        )
    }


    fun updateText1(value: String) = uiState.update { it.copy(inputText1 = value) }
    fun updateText2(value: String) = uiState.update { it.copy(inputText2 = value) }

}

class SomeStatefulEntity(
    savedState: SerializableContainer?,
) : InstanceKeeper.Instance {

    val state = MutableStateFlow(savedState?.consume(strategy = State.serializer()) ?: State())

    fun saveState(): SerializableContainer = SerializableContainer(value = state.value, strategy = State.serializer())

    override fun onDestroy() {
        Log.e("AAQQ", "onDestroy called")
    }

    @Serializable
    data class State(
        val inputText1: String = "",
        val inputText2: String = "",
    )
}