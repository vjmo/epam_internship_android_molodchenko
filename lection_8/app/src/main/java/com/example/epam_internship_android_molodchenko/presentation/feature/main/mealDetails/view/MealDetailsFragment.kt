package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.data.network.RetrofitInstance
import com.example.epam_internship_android_molodchenko.exten_fun.toMealDetailsUIModel
import com.example.epam_internship_android_molodchenko.data.repository.MealsDetailsRepositoryImpl
import com.example.epam_internship_android_molodchenko.databinding.FragmentMealDetailsBinding
import com.example.epam_internship_android_molodchenko.exten_fun.toMealDetailsEntity
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view.adapter.MealDetailsAdapter
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.viewModel.MealDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealDetailsFragment : Fragment() {

    private val binding: MealDetailsFragmentBinding by lazy { MealDetailsFragmentBinding.inflate(layoutInflater) }

    private val compositeDisposable = CompositeDisposable()

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
//перенсти все ф. во VM
    private fun callDetails() = arguments?.getInt(ID)?.let {
        mealsDetailsRepository.loadDetailsData(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.mealsDetails.first().toMealDetailsEntity() }
            .subscribe({ mealDetails ->
                val nameDetailsTextView = view?.findViewById<TextView>(R.id.name)
                val areaDetailsTextView = view?.findViewById<TextView>(R.id.area)
                val ingridientsDetailsTextView = view?.findViewById<TextView>(R.id.ingridients)

                nameDetailsTextView?.text = mealDetails.titleMeal
                areaDetailsTextView?.text = mealDetails?.areaMeal
                ingridientsDetailsTextView?.text = mealDetails?.ingredientsMeal

                Glide.with(view?.context).load(mealDetails.thumbMeal)
                    .into(view?.findViewById(R.id.details_img_id))

                mealDetailsAdapter.setList(mealDetails.tagMeal)
            },
                {
                    Log.e("MealDetails", "Error")
                    it.printStackTrace()
                })
    }?.let { compositeDisposable.add(it) }

    companion object {
        private const val ID = "ID"
        fun newInstance(id: Int) =
            MealDetailsFragment().apply {
                arguments = bundleOf(ID to id)
            }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}