package com.murillo.helpdeaftoy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.Response

@Dao
interface SerieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSerie(serie: Serie)

    @Query("SELECT * FROM serie_table ORDER BY id ASC")
    fun selectSeries(): LiveData<List<Serie>>

    @Query("DELETE FROM serie_table")
    fun nukeTable()

    /*
    @Query("SELECT * FROM serie_table WHERE numeroSerie IN (:numeroSerie)")
    fun getCadastro(numeroSerie: String): MutableLiveData<Serie>
    */
}