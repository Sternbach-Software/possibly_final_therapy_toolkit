package com.example.navigationdrawertemplate.ui.differentialTests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.navigationdrawertemplate.R

class DifferentialTestsFragment : Fragment() {

    private lateinit var differentialTestsViewModel: DifferentialTestsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        differentialTestsViewModel =
                ViewModelProviders.of(this).get(DifferentialTestsViewModel::class.java)
        val root = inflater.inflate(R.layout.plain_text_view_for_placeholder_fragment_layout, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        differentialTestsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}