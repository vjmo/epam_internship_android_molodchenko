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

        val textViewCountryMeal: TextView = view.findViewById(R.id.txt_japanese)
        val textViewName: TextView = view.findViewById(R.id.ter_chic_txt)

        textViewCountryMeal.text = arguments?.getString(COUNTRY_MEAL)
        textViewName.text = arguments?.getString(NAME)
    }

    companion object {
        private const val COUNTRY_MEAL = "Japanese"
        private const val NAME = "Teriyaki Chicken Casserole"
        fun newInstance(name: String, countryMeal: String) =
            MealDetailsFragment().apply {
                val args = bundleOf(NAME to name, COUNTRY_MEAL to countryMeal)
            }
    }
}