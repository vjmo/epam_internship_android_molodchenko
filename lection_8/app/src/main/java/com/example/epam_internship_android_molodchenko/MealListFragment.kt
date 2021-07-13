package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.repository.MealsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment() {


    lateinit var myApi: MealApi

    val recyclerViewCategory = view?.findViewById<RecyclerView>(R.id.rv_category)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    private val clickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(meal: Meal) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.host_fragment, MealDetailsFragment.newInstance(meal.id)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealsRV()
       // categoriesRV()
        categoryNet()
    }

    private fun mealsRV() {
        val recyclerViewMeal = view?.findViewById<RecyclerView>(R.id.rv_one)
        val recyclerViewAdapterMeal = MealAdapter(clickListenerMeal)

        recyclerViewMeal?.layoutManager = LinearLayoutManager(context)
        recyclerViewMeal?.adapter = recyclerViewAdapterMeal

        recyclerViewAdapterMeal.clickListener
        recyclerViewAdapterMeal.setList(listOf())

        recyclerViewAdapterMeal.clickListener = clickListenerMeal
    }
/*
    private fun categoriesRV() {

        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory?.adapter = recyclerViewAdapterCategory

        recyclerViewAdapterCategory.clickListener
        //recyclerViewAdapterCategory.setList(listOf())
    }*/

    private fun categoryNet() {
        //var recyclerViewAdapterCategory: CategoryAdapter
        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //recyclerViewCategory?.adapter = recyclerViewAdapterCategory

        //recyclerViewAdapterCategory.clickListener
        myApi = Common.mealApi
        myApi.getCategories().enqueue(
            object : Callback<MutableList<ModelCategory>> {
                override fun onResponse(
                    call: Call<MutableList<ModelCategory>>,
                    response: Response<MutableList<ModelCategory>>
                ) {
                    var recyclerViewAdapterCategory =
                        CategoryAdapter(context, response.body() as MutableList<ModelCategory>)
                    recyclerViewAdapterCategory.notifyDataSetChanged()
                    recyclerViewCategory?.adapter = recyclerViewAdapterCategory
                    //recyclerViewAdapterCategory.setList(response.body()!!)
                }

                override fun onFailure(call: Call<MutableList<ModelCategory>>, t: Throwable) {
                    toast("e r r o r!!!!!!!!")
                }
            }
        )
        /* val apiInCat = MealApi.create().getCategories()
         apiInCat.enqueue(
             object : retrofit2.Callback<List<Category>> {
                 override fun onResponse(
                     call: Call<List<Category>>,
                     response: Response<List<Category>>
                 ) {
                     recyclerViewAdapterCategory.setList(response.body()!!)
                 }

                 override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                     toast("e r r o r!!!!!!!!")
                 }*/
    }

    private fun mealNet() {
        val mealRepository = MealsRepository()
        val mealAdapter = MealAdapter(clickListenerMeal)
    }

    private fun toast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

}
