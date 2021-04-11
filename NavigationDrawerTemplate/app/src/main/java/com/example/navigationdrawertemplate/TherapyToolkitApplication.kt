package com.example.navigationdrawertemplate

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import com.example.navigationdrawertemplate.ui.homePageConditions.Condition
import java.io.File
import java.io.FileFilter
import kotlin.system.measureNanoTime

private const val TAG = "TherapyToolkitApplication"

class TherapyToolkitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Conditions.newInstance(this)
        readFilesAndInitializeConditions()
//      ConditionRepository.initialize(this)
    }

    companion object Conditions {
        lateinit var context: Context
        fun newInstance(context: Context) {
            this.context = context
        }
        val conditions = mutableListOf<Condition>()
        var conditionsToDisplay = mutableListOf<Condition>()
        val setOfAllPresentationsInDatabase = mutableSetOf<String>()
    }

    private fun readFilesAndInitializeConditions() {
        lateinit var conditionsParentFolder: File
        val contextWrapper = ContextWrapper(context).externalMediaDirs
        val parentFolder = contextWrapper[0]
        Log.d(TAG, "Parent Folder: $parentFolder")
        val filterOnlyDirectories = FileFilter { pathname -> pathname.isDirectory }
        val filterOnlyTextFiles = FileFilter { pathname -> pathname.extension == "txt" }
        try {
            conditionsParentFolder = parentFolder.listFiles(filterOnlyDirectories)!![0]
            Log.d(TAG, "ConditionsParentFolder: $conditionsParentFolder")
        } catch (e: Exception) {
            Log.e(
                TAG,
                "There was no file in ${parentFolder}!!!!!!"
            )
        }

        conditionsParentFolder.listFiles(filterOnlyDirectories)!!.sorted()
            .forEachIndexed { index, conditionFolder ->
                val nanoSeconds = measureNanoTime {
                    if (conditionFolder.isDirectory) {
                        val textFiles = conditionFolder.listFiles(filterOnlyTextFiles)!!.sorted()
                        //TODO I am counting on mommy that there will only be 4 files in the condition folder!!
                        //TODO I replaced declerations like val description = textFiles[0].readText() with
                        val title = conditionFolder.name
                        val bufferedReader1 = textFiles[0].bufferedReader()
                        val bufferedReader2 = textFiles[1].bufferedReader()
                        val bufferedReader3 = textFiles[2].bufferedReader()
                        val bufferedReader4 = textFiles[3].bufferedReader()

                        val description =bufferedReader1.readText()
                        val presentationsAndComments =bufferedReader2.readText()
                        val differentialTests =bufferedReader3.readText()
                        val pictures =bufferedReader4.readText()

                        bufferedReader1.close()
                        bufferedReader2.close()
                        bufferedReader3.close()
                        bufferedReader4.close()
                        val listOfPresentations = presentationsAndComments.substring(14,presentationsAndComments.indexOf("Comments: ")).split(",").map{it.trim()}.filterNot{it=="\n" || it == ""}
                        setOfAllPresentationsInDatabase.addAll(listOfPresentations)
                        val presentationsCommments = presentationsAndComments.substring(presentationsAndComments.indexOf("Comments: "))
                        conditions += Condition(
                            index,
                            title,
                            description,
                            listOfPresentations,
                            presentationsCommments,
                            differentialTests,
                            pictures
                        )
                    }
                }
                println("Folder ${index+1} took: $nanoSeconds")
            }
        setOfAllPresentationsInDatabase.forEach{Log.d(TAG,"Presentation: $it")}
        conditions.sortBy{it.title.first()}
        conditionsToDisplay = conditions.toMutableList()

    }

    fun initializeWithDummyData() {
        val condition1 = Condition(
            title = "Carpal Tunnel Syndrome (CTS)",
            pictures = "Presentations: Sore wrist, Impaired motor skills...(Backup Dummy Data)"
        )
        val condition2 = Condition(
            title = "Mild Dementia",
            pictures = "Presentations: Inability to remember where things are put down...(Backup Dummy Data)"
        )
        val condition3 = Condition(
            title = "Severe Dementia",
            pictures = "Presentations: Delusions of turning tiny bathrooms into beaches...(Backup Dummy Data)"
        )
        conditions.addAll(listOf(condition1, condition2, condition3))
        for (i in 4..20) conditions.add(
            Condition(
                title = "(Condition $i)",
                pictures = "Presentations: Lorem ipsum dolor sit amet, consectetur adipiscing..."
            )
        )
    }
}


