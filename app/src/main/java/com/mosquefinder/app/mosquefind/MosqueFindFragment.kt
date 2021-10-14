package com.mosquefinder.app.mosquefind

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mosquefinder.R

class MosqueFind : Fragment() {
    private lateinit var mosqueFindView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mosqueFindView =  inflater.inflate(R.layout.fragment_mosque_find, container, false)
        return mosqueFindView

    }
}