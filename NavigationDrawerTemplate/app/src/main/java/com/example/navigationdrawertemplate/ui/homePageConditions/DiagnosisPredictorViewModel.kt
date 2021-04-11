package com.example.navigationdrawertemplate.ui.homePageConditions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationdrawertemplate.TherapyToolkitApplication

val indicesOfInvisibleViews = mutableListOf<Int>()
val visibleConditions = TherapyToolkitApplication.conditions.toMutableList()
class DiagnosisPredictorViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is diagnosisPredictor Fragment"
    }
    val text: LiveData<String> = _text

}