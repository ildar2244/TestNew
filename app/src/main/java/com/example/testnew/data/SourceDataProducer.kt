package com.example.testnew.data


//источник данных - неизменяемый, т.е. как будто из внешней библиотеки
interface SourceDataProducer {

    fun produceDataFromSource(modelData: ModelData)
}