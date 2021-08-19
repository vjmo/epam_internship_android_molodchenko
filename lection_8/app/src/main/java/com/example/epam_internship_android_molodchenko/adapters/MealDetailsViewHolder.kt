package com.example.epam_internship_android_molodchenko.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R


class MealDetailsViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    var textTag: TextView = view.findViewById(R.id.txt_tag_details)
    fun bind(str: String) = run { textTag.text = str }
}