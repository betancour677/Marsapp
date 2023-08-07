package com.example.marsapp.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.marsapp.Model.MarsApiClass


@Dao
interface MarsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerrain(mars: MarsApiClass)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTerrains(mars: List<MarsApiClass>)



    @Update
    suspend fun updateTerrain(mars: MarsApiClass)

    @Delete
    suspend fun  deleteTerrain(mars: MarsApiClass)


    @Query("DELETE FROM mars_db_1")
    suspend fun deleteAll()


    // traer todos los terrenos

    @Query("SELECT * FROM mars_table ORDER BY id DESC")
    fun getAllTerrains(): LiveData<List<MarsApiClass>>


    @Query("SELECT * FROM mars_table WHERE type=:type Limit 1")
    fun getMarsType( type: String): LiveData<MarsApiClass>


    @Query("SELECT * FROM mars_table WHERE id=:id")
    fun getMarsId(id:Int): LiveData<MarsApiClass>

}