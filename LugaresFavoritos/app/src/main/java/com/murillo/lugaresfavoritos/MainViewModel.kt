package com.murillo.lugaresfavoritos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murillo.lugaresfavoritos.model.Localidade

class MainViewModel() : ViewModel()  {

    val localidadesBuscadas = MutableLiveData<List<Localidade>>()

}