package com.example.epam_internship_android_molodchenko

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

class HostFragment : Fragment((R.layout.fragment_host)) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.host_fragment, MealListFragment())
            .commit()
    }

    companion object {
        fun newInstance() = HostFragment()
    }
}