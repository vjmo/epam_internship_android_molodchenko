package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.exten_fun.toMealDetailsUIModel
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.repository.MealsDetailsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment() {

    private val mealsDetailsRepository by lazy { MealsDetailsRepositoryImpl(RetrofitInstance.mealApi) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callDetails()
    }

    private fun initView() {
        view?.findViewById<TextView>(R.id.ter_chic_txt)?.text = arguments?.getString(ID)
    }

    private fun callDetails() {

        requireArguments().getString(ID).let {
            mealsDetailsRepository.loadDetailsData(ID.toInt())
                .enqueue(object : Callback<ModelMealDetailsList> {
                    override fun onResponse(
                        call: Call<ModelMealDetailsList>,
                        response: Response<ModelMealDetailsList>
                    ) {
                        val mealResponse =
                            response.body()?.mealsDetails?.first()?.toMealDetailsUIModel()
                        // Glide.with(view?.context).load(mealResponse?.)
                    }

                    override fun onFailure(call: Call<ModelMealDetailsList>, t: Throwable) {
                        Log.e("Callback MealDetails", "Error")
                    }
                })

        }
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(modelMealDetails: Int) =
            MealDetailsFragment().apply {
                arguments = bundleOf(ID to modelMealDetails)
            }
    }
}