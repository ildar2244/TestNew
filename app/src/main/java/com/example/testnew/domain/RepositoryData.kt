package com.example.testnew.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface RepositoryData {

    fun getRepositoryFlowData(): Flow<BaseModel>
    fun getRepositoryData(): BaseModel
    fun fakeSourceData(scope: CoroutineScope?)
}