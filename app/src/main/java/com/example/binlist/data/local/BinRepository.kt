package com.example.binlist.data.local

import com.example.binlist.data.entities.BinModel
import com.example.binlist.data.remote.BinRemoteDataSource
import com.example.binlist.utils.performGetOperation
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val remoteDataSource: BinRemoteDataSource,
    private val localDataSource: BinDao
) {
    fun getBin(cardNumber: Int) = performGetOperation(
        databaseQuery = { localDataSource.getAllBins() },
        networkCall = { remoteDataSource.getBin(cardNumber) },
        saveCallResult = { localDataSource.insert(BinModel(it)) }

    )
}