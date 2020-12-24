package com.example.testnew.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.testnew.domain.BaseModel
import com.example.testnew.domain.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExViewModel : ViewModel(), LifecycleObserver {

    private val useCase: UseCase = UseCase()

    private val _baseModel = MutableLiveData<BaseModel>()
    val baseModel: LiveData<BaseModel> = _baseModel

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d("9999", "Lifecycle: RESUME")
        useCase.fakeStart()

        updateFlowData()
        updateData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d("9999", "Lifecycle: PAUSE")
        useCase.fakeStop()
    }

    private fun updateFlowData() {
        CoroutineScope(Dispatchers.Default).launch {
            val rd = useCase.getFlowDataFromRepo().collect {
                Log.d("9999", "viewModel: FLOW-$it")
                withContext(Dispatchers.Main) {
                    _baseModel.value = it
                }
            }
        }

    }

    private fun updateData() {
        //_baseModel.value = useCase.getDataFromRepo()
    }
}