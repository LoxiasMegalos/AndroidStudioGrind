package com.murillo.helpdeaftoy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.murillo.helpdeaftoy.data.Serie

import com.murillo.helpdeaftoy.data.SerieDatabase
import com.murillo.helpdeaftoy.data.SerieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {

    val selectSeries: LiveData<List<Serie>>

    val repository: SerieRepository

    var serieLogada = MutableLiveData<Serie>()

    init {
        val serieDao = SerieDatabase.getDatabase(application).serieDao()
        repository = SerieRepository(serieDao)
        selectSeries = repository.selectSeries
    }

    fun addSerie(serie: Serie){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSerie(serie)
        }
    }

    /*
    fun getDados(numeroSerie: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCadastro(numeroSerie)
            serieLogada = response
        }
    }*/

    fun nukeTable(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.nukeTable()
        }
    }


}