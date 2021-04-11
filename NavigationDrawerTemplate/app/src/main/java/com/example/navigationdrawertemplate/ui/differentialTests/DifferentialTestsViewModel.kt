package com.example.navigationdrawertemplate.ui.differentialTests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DifferentialTestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is differentialTests Fragment"
    }
    val text: LiveData<String> = _text
}