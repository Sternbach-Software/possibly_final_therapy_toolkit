//@file:JvmName("ConditionListFragmentKt")

package com.example.navigationdrawertemplate.ui.homePageConditions
/*

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.navigationdrawertemplate.R
import java.util.*

private const val TAG = "ConditionListFragment"
var counter = 0

@Entity
data class Condition(
    @PrimaryKey
    val id: Int = counter++,
    var title: String = "",
    var description: String = "",
    var presentations: List<String> = listOf(),
    var comments: String = "",
    var differentialTests: String = "",
    var pictures: String = "",
    var visibility: Int = View.VISIBLE
)


public var callbacks: ConditionsListFragment.Callbacks? = null
class FilterCondition(){
    fun matches(condition: Condition?): Boolean {
        var conditionContainsRequiredPresentations: Boolean = false //TODO Starting out with the assumption that it doesn't match may be the source of bugs which arent't displaying enough. Assuming it does can be source of bugs which are displaying too much.
        var conditionContainsRequiredText: Boolean = false
        if (requiredPresentations == mutableSetOf<String>() && textWhichUserSearchedFor == "") {
            return true
        } else {
            if (requiredPresentations != mutableSetOf<String>()) {
                for (presentation in requiredPresentations.map { it.toLowerCase(Locale.ROOT) }) {
                    if (presentation in condition?.presentations?.map { it.toLowerCase(Locale.ROOT) } ?: mutableListOf()) {//TODO I am  not sure what side effects using " ?: mutableListOf()" will have
                        conditionContainsRequiredPresentations = true
                    }
                }
            }
            if (textWhichUserSearchedFor != "") {
                conditionContainsRequiredText = condition?.description?.contains(
                    textWhichUserSearchedFor,
                    ignoreCase = true
                ) != false || condition.title.contains(
                    textWhichUserSearchedFor, ignoreCase = true
                )
            }
        }
        if((textWhichUserSearchedFor != "" && conditionContainsRequiredText) && (requiredPresentations != mutableSetOf<String>() && conditionContainsRequiredPresentations)){//If there was a text requirement AND a presentations requirement, and it met both, return "it matches"
            return true
        }
        if((textWhichUserSearchedFor == "") && (requiredPresentations != mutableSetOf<String>() && conditionContainsRequiredPresentations)){
            return true
        }
        if((textWhichUserSearchedFor != "" && conditionContainsRequiredText) && requiredPresentations == mutableSetOf<String>()){
            return true
        }
        return false
    }
}


var textWhichUserSearchedFor = ""
var requiredPresentations = mutableSetOf<String>()

class ConditionsListFragment : Fragment() {
    private lateinit var conditionRecyclerView: RecyclerView
    private var adapter: ConditionAdapter? = ConditionAdapter(context, visibleConditions)

    interface Callbacks {
        fun onConditionSelected(conditionId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.conditions_recycler_view, container, false)
        conditionRecyclerView = view.findViewById(R.id.conditions_recycler_view) as RecyclerView
        conditionRecyclerView.layoutManager = LinearLayoutManager(context)
        conditionRecyclerView.adapter = adapter
        return view
    }

    override fun onStart() {
        super.onStart()

        */
/* conditionsListViewModel.conditionListLiveData.observe(
             viewLifecycleOwner,
             { conditions ->
                 conditions?.let {
                     Log.i(TAG, "Got conditionLiveData ${conditions.size}")
                     updateUI(conditions)
                 }
             }
         )*//*


        updateUI(visibleConditions)
    }

    private fun updateUI(conditions: MutableList<Condition>) {
        adapter?.let { conditionAdapter ->
            conditionAdapter.conditions = conditions
        } ?: run {
            adapter = ConditionAdapter(context,conditions)
        }
        conditionRecyclerView.adapter = adapter
        Log.d("UpdateUi", "UI updated.")
    }

    private class ConditionHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        private lateinit var condition: Condition
        val conditionTitleTextView: TextView = itemView.findViewById(R.id.list_item_condition_title)
        val descriptionTextView*/
/*conditionSubtitleTextView*//*
: TextView =
            itemView.findViewById(R.id.list_item_condition_information)

        fun bind(condition1: Condition) {
            */
/*if (condition1.title == "") {
                itemView.visibility = View.GONE
                itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                visibleConditions.minusAssign(condition1)
                return
            }*//*

            this.condition = condition1
            conditionTitleTextView.text = this.condition.title
            descriptionTextView.text = this.condition.description
        }

        override fun onClick(v: View) {
            callbacks?.onConditionSelected(condition.id)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    */
/*private inner class ConditionAdapter(var conditions: MutableList<Condition>) :
        RecyclerView.Adapter<ConditionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ConditionHolder {
            val view = layoutInflater.inflate(R.layout.condition_list_item, parent, false)

            return ConditionHolder(view)
        }

        override fun getItemCount() = visibleConditions.size

        override fun onBindViewHolder(holder: ConditionHolder, position: Int) {
            val condition = visibleConditions[position]
                holder.bind(condition)
             }

        *//*
*/
/*fun performFiltering(string: String): MutableList<Condition> {
            val listOfMatchingConditions: MutableList<Condition> = ArrayList()
            if (string.isEmpty()) {
                conditionsListFiltered = TherapyToolkitApplication.conditions
            } else {
                for (condition in TherapyToolkitApplication.conditions) {

                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    //TODO add options to limit search to search within description, notes, etc.
                    if (condition.title.toLowerCase(Locale.ROOT).contains(
                            string.toLowerCase(
                                Locale.ROOT
                            )
                        )
                    ) {
                        listOfMatchingConditions.add(condition)
                    }
                }
            }
            return listOfMatchingConditions
        }

        fun performFiltering(presentations: MutableList<String>): MutableList<Condition> {
            val listofMatchingConditions: MutableList<Condition> = mutableListOf()
            if (presentations.isEmpty()) {
                conditionsListFiltered = TherapyToolkitApplication.conditions
            } else {
                for (presentation in presentations) for (condition in TherapyToolkitApplication.conditions) {

                    // name match condition. this might differ depending on your requirement
                    // here we are looking for name or phone number match
                    //TODO add options to limit search to search within description, notes, etc.
                    if (presentation in condition.presentations) {
                        listofMatchingConditions.add(condition)
                    }
                }
            }
            return listofMatchingConditions
        }*//*
*/
/*
    }*//*

    private class ConditionAdapter(context: Context?, var conditions: MutableList<Condition>) :
        MatchableRVArrayAdapter<Condition?, ConditionHolder?>(
            context,
            R.layout.condition_list_item,
            LinkedList(conditions)
        ) {

        override fun onCreateHolder(view: View): ConditionHolder {
            return ConditionHolder(view)
        }

        override fun onBindHolder(condition: Condition?, holder: ConditionHolder?, position: Int) {
            holder?.conditionTitleTextView?.text = condition?.title
            holder?.descriptionTextView?.text = condition?.description
        }

        override fun matches(condition: Condition?): Boolean {
            return FilterCondition().matches(condition)
        }

        fun doesItMatch(condition: Condition?): Boolean {
            var conditionContainsRequiredPresentations: Boolean = false //TODO Starting out with the assumption that it doesn't match may be the source of bugs which arent't displaying enough. Assuming it does can be source of bugs which are displaying too much.
            var conditionContainsRequiredText: Boolean = false
            if (requiredPresentations == mutableSetOf<String>() && textWhichUserSearchedFor == "") {
                return true
            } else {
                if (requiredPresentations != mutableSetOf<String>()) {
                    for (presentation in requiredPresentations.map { it.toLowerCase(Locale.ROOT) }) {
                        if (presentation in condition?.presentations?.map { it.toLowerCase(Locale.ROOT) } ?: mutableListOf()) {//TODO I am  not sure what side effects using " ?: mutableListOf()" will have
                            conditionContainsRequiredPresentations = true
                        }
                    }
                }
                if (textWhichUserSearchedFor != "") {
                    conditionContainsRequiredText = condition?.description?.contains(
                        textWhichUserSearchedFor,
                        ignoreCase = true
                    ) != false || condition.title.contains(
                        textWhichUserSearchedFor, ignoreCase = true
                    )
                }
            }
            if((textWhichUserSearchedFor != "" && conditionContainsRequiredText) && (requiredPresentations != mutableSetOf<String>() && conditionContainsRequiredPresentations)){//If there was a text requirement AND a presentations requirement, and it met both, return "it matches"
                return true
            }
            if((textWhichUserSearchedFor == "") && (requiredPresentations != mutableSetOf<String>() && conditionContainsRequiredPresentations)){
                return true
            }
            if((textWhichUserSearchedFor != "" && conditionContainsRequiredText) && requiredPresentations == mutableSetOf<String>()){
                return true
            }
            return false
        }
    }
}*/
