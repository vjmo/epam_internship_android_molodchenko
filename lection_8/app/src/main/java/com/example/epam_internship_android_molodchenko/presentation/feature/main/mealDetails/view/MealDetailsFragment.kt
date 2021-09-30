package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.data.network.RetrofitInstance
import com.example.epam_internship_android_molodchenko.data.repository.MealsDetailsRepositoryImpl
import com.example.epam_internship_android_molodchenko.databinding.FragmentMealDetailsBinding
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealDetailsUseCase
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view.adapter.MealDetailsAdapter
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.viewModel.MealDetailsViewModel
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.viewModel.MealDetailsViewModelFactory
import io.reactivex.disposables.CompositeDisposable

class MealDetailsFragment : Fragment() {

    private lateinit var viewBinding: FragmentMealDetailsBinding

    private val viewModel: MealDetailsViewModel by viewModels {
        MealDetailsViewModelFactory(
            GetMealDetailsUseCase(
                MealsDetailsRepositoryImpl(
                    (RetrofitInstance.mealApi)
                )
            )
        )
    }

    private val compositeDisposable = CompositeDisposable()

    //private val mealsDetailsRepository by lazy { MealsDetailsRepositoryImpl(RetrofitInstance.mealApi) }

    private val mealDetailsAdapter = MealDetailsAdapter()

    private val recyclerViewMealDetails by lazy { view?.findViewById<RecyclerView>(R.id.rv_tags) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        //  return inflater.inflate(R.layout.fragment_meal_details, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerViewMealDetails?.adapter = mealDetailsAdapter
        recyclerViewMealDetails?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    }


    private fun initView() {
        requireArguments().getInt(ID).let { viewModel.startReceivingMealDetails(it) }
        viewModel.mealDetailsUIModel.observe(viewLifecycleOwner, {

            viewBinding.name.text = it.first().nameMealDetails
            viewBinding.area.text = it.first().area
            viewBinding.ingridients.text = it.first().ingredients

            Glide.with(view?.context).load(it.first().mealThumb)
                .into(viewBinding.detailsImgId)

            mealDetailsAdapter.setList(it.first().tags)
        })
    }

    //перенсти все ф. во VM
    /*private fun callDetails() = arguments?.getInt(ID)?.let {
        mealsDetailsRepository.loadDetailsData(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it..first().toMealDetailsEntity() }
            .subscribe({ /*mealDetails ->
                val nameDetailsTextView = view?.findViewById<TextView>(R.id.name)
                val areaDetailsTextView = view?.findViewById<TextView>(R.id.area)
                val ingridientsDetailsTextView = view?.findViewById<TextView>(R.id.ingridients)

                nameDetailsTextView?.text = mealDetails.titleMeal
                areaDetailsTextView?.text = mealDetails?.areaMeal
                ingridientsDetailsTextView?.text = mealDetails?.ingredientsMeal

                Glide.with(view?.context).load(mealDetails.thumbMeal)
                    .into(view?.findViewById(R.id.details_img_id))

                mealDetailsAdapter.setList(mealDetails.tagMeal)*/
            },
                {
                    Log.e("MealDetails", "Error")
                    it.printStackTrace()
                })
    }?.let { compositeDisposable.add(it) }*/

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
