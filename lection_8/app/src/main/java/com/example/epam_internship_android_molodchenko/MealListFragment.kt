package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MealListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    val mealOne = Meal(
        1, "Teriyaki Chicken Casserole", "JAPANESE",
        " ", " Soy sauce - 3/4 cup\n" +
                "        Water - 1/2 cup\n" +
                "        Brown sugar - 1/4 cup\n" +
                "        Ground ginger - 1/2 teaspoon\n" +
                "        Minced garlic - 1/2 teaspoon\n" +
                "        Cornstarch - 4 tablespoons\n" +
                "        Chicken breasts - 2\n" +
                "        Stir-fry vegetables - 1 (12 oz.)\n" +
                "        Brown rice - 3 cups"
    )

    val itemMealList: List<Meal> = listOf(mealOne)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.meal_one_img)
        val textView: TextView = view.findViewById(R.id.txt_itm_meal_one)

        textView.setOnClickListener {
            setOnClickParentFrMan()
        }
        imageView.setOnClickListener {
            setOnClickParentFrMan()
        }

    }

    private fun setOnClickParentFrMan() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.host_fragment,
                MealDetailsFragment.newInstance(mealOne.name, mealOne.country),
                ""
            )
            .addToBackStack(null)
            .commit()
    }

    companion object{
        fun newInstance() = MealListFragment()
    }

}