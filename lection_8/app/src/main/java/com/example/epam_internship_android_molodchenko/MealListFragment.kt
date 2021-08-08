package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.uimodel.MealUIModel

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
        override fun onItemClick(mealUIModel: MealUIModel) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.host_fragment, MealDetailsFragment.newInstance(mealUIModel.id)
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
        var recyclerViewAdapterCategory: CategoryAdapter? = null
        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //recyclerViewCategory?.adapter = recyclerViewAdapterCategory

        //recyclerViewAdapterCategory.clickListener
       // myApi = Common.mealApi
        myApi.getCategories().enqueue(
            /*object : Callback<MutableList<ModelCategory>> {
                override fun onResponse(
                    call: Call<MutableList<ModelCategory>>,
                    response: Response<MutableList<ModelCategory>>
                ) {
                    recyclerViewAdapterCategory =
                        CategoryAdapter(context, response.body() as MutableList<ModelCategory>)
                    //recyclerViewAdapterCategory.notifyDataSetChanged()
                    recyclerViewCategory?.adapter = recyclerViewAdapterCategory
                    //recyclerViewAdapterCategory.setList(response.body()!!)
                }

                override fun onFailure(call: Call<MutableList<ModelCategory>>, t: Throwable) {
                    toast("e r r o r!!!!!!!!")
                }*/
            }
        )
        /* val apiInCat = MealApi.create().getCategories()
         apiInCat.enqueue(
             object : retrofit2.Callback<List<CategoryUIModel>> {
                 override fun onResponse(
                     call: Call<List<CategoryUIModel>>,
                     response: Response<List<CategoryUIModel>>
                 ) {
                     recyclerViewAdapterCategory.setList(response.body()!!)
                 }

                 override fun onFailure(call: Call<List<CategoryUIModel>>, t: Throwable) {
                     toast("e r r o r!!!!!!!!")*/
                 }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

}
