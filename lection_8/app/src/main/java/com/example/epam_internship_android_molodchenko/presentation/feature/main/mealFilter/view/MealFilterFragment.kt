package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealFilter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.epam_internship_android_molodchenko.databinding.BottomSheetBinding
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealFilter.view.clickListener.OnItemClickListenerFilter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MealFilterFragment : BottomSheetDialogFragment() {

    private lateinit var viewBinding: BottomSheetBinding

    var clickListener: OnItemClickListenerFilter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = BottomSheetBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.descBttn.setOnClickListener {
            clickListener?.onItemClick(false)
            dismiss()
        }
        viewBinding.ascBttn.setOnClickListener {
            clickListener?.onItemClick(true)
            dismiss()
        }

    }

    companion object {
        fun newInstance(): MealFilterFragment = MealFilterFragment()
    }
}