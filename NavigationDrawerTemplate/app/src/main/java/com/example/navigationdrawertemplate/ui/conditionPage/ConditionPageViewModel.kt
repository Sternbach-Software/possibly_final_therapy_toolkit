package com.example.navigationdrawertemplate.ui.conditionPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.navigationdrawertemplate.ui.homePageConditions.Condition

class ConditionPageViewModel: ViewModel() {
    lateinit var condition:Condition
    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        when (it) {
            1 -> condition.description
            2 -> "Presentations: ${condition.presentations}"
            3 -> condition.differentialTests
            4 -> condition.pictures
            else -> ""
        }
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
//    private val conditionRepository = ConditionRepository.get()
//    val conditionListLiveData = conditionRepository.getConditions()
//
//    fun addCrime(condition: Condition) {
//        conditionRepository.addCondition(condition)
//    }
}