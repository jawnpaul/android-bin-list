package com.example.binlist.data.remote

import javax.inject.Inject

class BinRemoteDataSource @Inject constructor(
    private val binApiService: BinApiService
) : BaseDataSource() {

    suspend fun getBin(cardNumber: Int) = getResult { binApiService.getBinNumber(cardNumber) }
}