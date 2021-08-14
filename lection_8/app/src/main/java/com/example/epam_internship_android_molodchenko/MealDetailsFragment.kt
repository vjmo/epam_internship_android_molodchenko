package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.adapters.MealDetailsAdapter
import com.example.epam_internship_android_molodchenko.exten_fun.toMealDetailsUIModel
import com.example.epam_internship_android_molodchenko.models.ModelMealDetailsList
import com.example.epam_internship_android_molodchenko.repository.MealsDetailsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment() {

    private val mealsDetailsRepository by lazy { MealsDetailsRepositoryImpl(RetrofitInstance.mealApi) }

    private val mealDetailsAdapter = MealDetailsAdapter()

    private val recyclerViewMealDetails by lazy { view?.findViewById<RecyclerView>(R.id.rv_tags) }

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
        recyclerViewMealDetails?.adapter = mealDetailsAdapter
        recyclerViewMealDetails?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun callDetails() {

        arguments?.getInt(ID)?.let {
            mealsDetailsRepository.loadDetailsData(it)
                .enqueue(object : Callback<ModelMealDetailsList> {
                    override fun onResponse(
                        call: Call<ModelMealDetailsList>,
                        response: Response<ModelMealDetailsList>
                    ) {
                        val mealDetailsResponse = response.body()?.mealsDetails?.first()?.toMealDetailsUIModel()

                        val nameDetailsTextView = view?.findViewById<TextView>(R.id.name)
                        val areaDetailsTextView = view?.findViewById<TextView>(R.id.area)
                        val ingridientsDetailsTextView = view?.findViewById<TextView>(R.id.ingridients)

                        nameDetailsTextView?.text = mealDetailsResponse?.nameMealDetails
                        areaDetailsTextView?.text = mealDetailsResponse?.area
                        ingridientsDetailsTextView?.text = mealDetailsResponse?.ingredients

                        Glide.with(view?.context).load(mealDetailsResponse?.mealThumb).into(view?.findViewById(R.id.details_img_id))
                        Glide.with(view?.context).load(mealDetailsResponse?.youtube).into(view?.findViewById(R.id.video_img_id))

                          response.body()?.mealsDetails?.let {
                                    if (mealDetailsResponse != null) {
                                        mealDetailsAdapter.setList(mealDetailsResponse.tags)
                                    }
                                }
                    }

                    override fun onFailure(call: Call<ModelMealDetailsList>, t: Throwable) {
                        Log.e("Callback MealDetails", "Error")
                    }
                })
        }

    }

    companion object {
        private const val ID = "ID"
        fun newInstance(id: Int) =
            MealDetailsFragment().apply {
                arguments = bundleOf(ID to id)
            }
    }
}