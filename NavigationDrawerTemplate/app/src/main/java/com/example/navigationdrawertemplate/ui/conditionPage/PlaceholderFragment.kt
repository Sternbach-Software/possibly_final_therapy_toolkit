package com.example.navigationdrawertemplate.ui.conditionPage

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.navigationdrawertemplate.R
import com.example.navigationdrawertemplate.TherapyToolkitApplication

/**
 * A placeholder fragment containing a simple view.
 */
private const val TAG = "PlaceholderFragment"

class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: ConditionPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val conditionId = arguments?.get("CONDITION") as Int? ?: 1
        pageViewModel = ViewModelProviders.of(this).get(ConditionPageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            condition = TherapyToolkitApplication.conditions[conditionId]
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.plain_text_view_for_placeholder_fragment_layout, container, false)


        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        textView.movementMethod = ScrollingMovementMethod()


        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, conditionId: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putInt("CONDITION",conditionId)
//                    putSerializable("CONDITION", Condition(,) to Any::class.java)
                }
            }
        }
    }
}