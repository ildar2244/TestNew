package com.example.testnew.data

import android.util.Log
import com.example.testnew.domain.BaseModel
import com.example.testnew.domain.RepositoryData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class Repository : SourceDataProducer, RepositoryData {

    //интерфейс-продюссер данных, подписываемся на 'прослушку' (не можем его изменить)
    override fun produceDataFromSource(modelData: ModelData) {
        Log.d("9999", "producer: $modelData")

        //TODO - Как отсюда передать данные в UseCase или здесь в: 'getRepositoryFlowData()' или 'getRepositoryData()'
    }

    override fun getRepositoryFlowData(): Flow<BaseModel> = flow {
        Log.d("9999", "repository: FLOW")
        //TODO здесь,например, код маппинга из 'ModelData' в 'BaseModel'
        emit(BaseModel())
    }

    override fun getRepositoryData(): BaseModel {
        TODO("Not yet implemented")
    }

    //имитируем исчтоник каких-то данных (например, CAN-шина или из Сервера)
    override fun fakeSourceData(scope: CoroutineScope?) {
        scope?.let {
            it.launch {
                while (it.isActive) {
                    withContext(Dispatchers.Main) {
                        val p1 = (-40..210).random()
                        val p2 = (0..255).random()
                        val fakeData = ModelData(one = p1, two = p2)
                        produceDataFromSource(fakeData)
                    }
                    delay(10000) //10 seconds
                }
            }
        }
    }
}