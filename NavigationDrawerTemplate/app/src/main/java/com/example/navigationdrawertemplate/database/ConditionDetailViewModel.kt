package com.example.navigationdrawertemplate.database
/*

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.navigationdrawertemplate.database.ConditionRepository
import com.example.navigationdrawertemplate.ui.homePageConditions.Condition
import java.util.*

class ConditionDetailViewModel : ViewModel() {

    private val conditionRepository = ConditionRepository.get()
    private val conditionIdLiveData = MutableLiveData<UUID>()

    val conditionLiveData: LiveData<Condition?> =
        Transformations.switchMap(conditionIdLiveData) { conditionId ->
            conditionRepository.getConditions(conditionId)
        }
    
    fun loadCondition(conditionId: UUID) {
        conditionIdLiveData.value = conditionId
    }

    fun saveCondition(condition: Condition) {
        conditionRepository.updateCondition(condition)
    }
}
*/
