package com.generation.desenvolvmed

import androidx.lifecycle.ViewModel
import com.generation.desenvolvmed.api.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
    ) : ViewModel(){

}