package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.epam_internship_android_molodchenko.models.ModelMealDetails

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
        view.findViewById<TextView>(R.id.ter_chic_txt).text = arguments?.getString(ID)
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(modelMealDetails: Int) =
            MealDetailsFragment().apply {
                arguments = bundleOf(ID to modelMealDetails)
            }
    }
}