package com.murillo.helpdeaftoy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Response

class SerieRepository(private val serieDao: SerieDao) {

    val selectSeries = serieDao.selectSeries()

    fun addSerie(serie: Serie){
        serieDao.addSerie(serie)
    }

    fun nukeTable(){
        serieDao.nukeTable()
    }

    /*
    fun getCadastro(numeroSerie: String) : MutableLiveData<Serie> {
        return serieDao.getCadastro(numeroSerie)
    }*/
}