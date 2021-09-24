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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailsFragment : Fragment() {

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
            .map { it.mealsDetails.first().toMealDetailsUIModel() }
            .subscribe({ mealDetails ->
                val nameDetailsTextView = view?.findViewById<TextView>(R.id.name)
                val areaDetailsTextView = view?.findViewById<TextView>(R.id.area)
                val ingridientsDetailsTextView = view?.findViewById<TextView>(R.id.ingridients)

                nameDetailsTextView?.text = mealDetails.nameMealDetails
                areaDetailsTextView?.text = mealDetails?.area
                ingridientsDetailsTextView?.text = mealDetails?.ingredients

                Glide.with(view?.context).load(mealDetails.mealThumb)
                    .into(view?.findViewById(R.id.details_img_id))

                mealDetailsAdapter.setList(mealDetails.tags)
            },
                {
                    Log.e("MealDetails", "Error")
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