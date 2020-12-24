package com.example.testnew.domain

import com.example.testnew.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive

class UseCase {

    private val repository: RepositoryData = Repository()

    private lateinit var job: Job
    private var scope: CoroutineScope? = null

    fun getFlowDataFromRepo(): Flow<BaseModel> = repository.getRepositoryFlowData()

    fun getDataFromRepo(): BaseModel = repository.getRepositoryData()

    fun fakeStart() {
        job = Job()
        scope = CoroutineScope(Dispatchers.Main + job)
        scope?.let { repository.fakeSourceData(it) }
    }

    fun fakeStop() {
        scope?.let { if (it.isActive) job.cancel() }
    }
}