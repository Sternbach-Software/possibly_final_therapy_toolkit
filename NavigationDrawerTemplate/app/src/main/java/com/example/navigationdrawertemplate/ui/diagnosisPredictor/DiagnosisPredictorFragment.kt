package com.example.navigationdrawertemplate.ui.diagnosisPredictor

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
//import androidx.room.Entity
//import androidx.room.PrimaryKey
import com.example.navigationdrawertemplate.R
import com.example.navigationdrawertemplate.TherapyToolkitApplication
import com.example.navigationdrawertemplate.ui.homePageConditions.Condition
import java.util.*
import kotlin.collections.ArrayList
private const val TAG = "DiagnosisPredictorFragment"


private var callbacks: DiagnosisPredictorFragment.Callbacks? = null
var counter = 0

    class DiagnosisPredictorFragment : Fragment() {
        private lateinit var conditionRecyclerView: RecyclerView
        private var adapter: ConditionAdapter? = ConditionAdapter(emptyList())

        interface Callbacks {
            fun onConditionSelected(conditionId: Int)
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
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

            /* conditionsListViewModel.conditionListLiveData.observe(
                 viewLifecycleOwner,
                 { conditions ->
                     conditions?.let {
                         Log.i(TAG, "Got conditionLiveData ${conditions.size}")
                         updateUI(conditions)
                     }
                 }
             )*/
            updateUI(TherapyToolkitApplication.conditions)
        }

        private fun updateUI(conditions: List<Condition>) {
            adapter?.let {
                it.conditions = conditions
            } ?: run {
                adapter = ConditionAdapter(conditions)
            }
            conditionRecyclerView.adapter = adapter
            Log.d("UpdateUi", "UI updated.")
        }

        class ConditionHolder(view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            init {
                itemView.setOnClickListener(this)
            }

            private lateinit var condition: Condition
            val conditionTitleTextView: TextView = itemView.findViewById(R.id.list_item_condition_title)
            val descriptionTextView/*conditionSubtitleTextView*/: TextView =
                itemView.findViewById(R.id.list_item_condition_information)

            fun bind(condition1: Condition) {
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


        private inner class ConditionAdapter(var conditions: List<Condition>) :
            RecyclerView.Adapter<ConditionHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : ConditionHolder {
                val view = layoutInflater.inflate(R.layout.condition_list_item_with_number_of_matched_conditions, parent, false)
                return ConditionHolder(view)
            }

            override fun getItemCount() = conditions.size

            override fun onBindViewHolder(holder: ConditionHolder, position: Int) {
                val condition = conditions[position]
                holder.bind(condition)
            }
        }
    }