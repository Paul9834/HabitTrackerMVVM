package com.example.habittrackermvvm.ui.fragments.habitlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.habittrackermvvm.R
import kotlinx.android.synthetic.main.fragment_habit_list.*


class HabitListFragment : Fragment(R.layout.fragment_habit_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_createHabitFragment)
        }

    }




}