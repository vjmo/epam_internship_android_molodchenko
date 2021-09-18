package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {

    //кликлист будет равняться налл
    var clickListener: OnItemClickListenerFilter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //здесь эти 2 кнопки объявить
        val buttonSortAsc = view.findViewById<Button>(R.id.asc_bttn)
        val buttonSortDesc = view.findViewById<Button>(R.id.desc_bttn)
        // здесь вызываю метод Кликлистнер по нажатию на кнопку , при этом сделать метод , который будет сеттить кликлистнер
        buttonSortDesc.setOnClickListener {
            clickListener?.onItemClick(false)
        }
        buttonSortAsc.setOnClickListener {
            clickListener?.onItemClick(true)
        }

    }

    //метод для сетта клика = присваивани

    companion object {
        fun newInstance(): FilterFragment = FilterFragment()
    }
}