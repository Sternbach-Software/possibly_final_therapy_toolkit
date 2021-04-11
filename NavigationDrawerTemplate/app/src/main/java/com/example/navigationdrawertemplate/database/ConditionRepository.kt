package com.example.navigationdrawertemplate.database
/*

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.navigationdrawertemplate.ui.homePageConditions.Condition
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "condition-database"

class ConditionRepository(context: Context) {

    private val database : ConditionDatabase = Room.databaseBuilder(
        context.applicationContext,
        ConditionDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val conditionDao = database.conditionDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getConditions(): LiveData<List<Condition>> = conditionDao.getConditions()

    fun getConditions(id: UUID): LiveData<Condition?> = conditionDao.getCondition(id)

    fun updateCondition(condition: Condition) {
        executor.execute {
            conditionDao.updateCondition(condition)
        }
    }

    fun addCondition(condition: Condition) {
        executor.execute {
            conditionDao.addCondition(condition)
        }
    }

    companion object {
        private var INSTANCE: ConditionRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ConditionRepository(context)
            }
        }

        fun get(): ConditionRepository {
            return INSTANCE ?:
            throw IllegalStateException("ConditionRepository must be initialized")
        }
    }
}*/
