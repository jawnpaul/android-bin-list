package com.example.binlist.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlist.data.entities.BinModel

@Dao
interface BinDao {
    @Query("SELECT * FROM bins_table")
    fun getAllBins(): LiveData<List<BinModel>>

    @Query("SELECT * FROM bins_table WHERE localId = :id")
    fun getBin(id: Int): LiveData<BinModel>

    @Query("DELETE FROM bins_table")
    suspend fun deleteAll()

    /*@Query("SELECT * FROM bins_table")
    fun getBin(id: Int): LiveData<BinModel>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bin: BinModel)
}