package com.generation.viewmodelelivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    /*
    LiveData
    MutableLiveData
     */

    var cont = MutableLiveData(0)

    fun addNum(){
        cont.value = cont.value?.plus(1)
    }

}