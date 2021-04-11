package com.example.navigationdrawertemplate.ui.homePageConditions

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.navigationdrawertemplate.TherapyToolkitApplication.Conditions.setOfAllPresentationsInDatabase

class FilterDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = mutableListOf<String>() // Where we track the selected items
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle("Select Presentations")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(
                    setOfAllPresentationsInDatabase.toTypedArray(), null
                ) { _, which, isChecked ->
                    val givenItemInListOfAllPresentationsInDatabase = setOfAllPresentationsInDatabase.toList()[which]
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        selectedItems.add(givenItemInListOfAllPresentationsInDatabase)
                    } else if (givenItemInListOfAllPresentationsInDatabase in selectedItems) {
                        // Else, if the item is already in the array, remove it
                        selectedItems.remove(givenItemInListOfAllPresentationsInDatabase)
                    }
                }
                // Set the action buttons
                .setPositiveButton("Filter"
                ) { _, _ ->
                    // User clicked OK, so save the selectedItems results somewhere
                    // or return them to the component that opened the dialog
                    requiredPresentations.addAll(selectedItems)
                }
                .setNegativeButton("Cancel"
                ) { _, _ ->
                    selectedItems.clear()
                }.setNeutralButton("Clear Filters"){ _: DialogInterface, _: Int ->
                    requiredPresentations.clear()
                    textWhichUserSearchedFor=""
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
