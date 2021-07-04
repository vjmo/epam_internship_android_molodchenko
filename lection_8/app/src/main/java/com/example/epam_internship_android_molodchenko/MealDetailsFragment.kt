package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class MealDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.ter_chic_txt).text = arguments?.getString(NAME)
    }

    companion object {
        private const val NAME = "Title"
        fun newInstance(name: String) =
            MealDetailsFragment().apply {
                arguments = bundleOf(NAME to name)
            }
    }
}