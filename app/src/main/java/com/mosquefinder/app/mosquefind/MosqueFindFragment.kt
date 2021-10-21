package com.mosquefinder.app.mosquefind

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.google.firebase.firestore.*
import com.mosquefinder.app.home.FirebaseService

class MosqueFind : Fragment() {
    private lateinit var mosqueFindView: View
    private lateinit var recyclerView: RecyclerView
    private var masjidList: ArrayList<MasjidModel> = ArrayList()
    private var mosqueFindAdapter: MosqueFindAdapter = MosqueFindAdapter(masjidList)
    private lateinit var dbref: FirebaseFirestore

    private val firebaseService: FirebaseService = FirebaseService()

    private val TAG = "Firebase Error"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mosqueFindView =  inflater.inflate(R.layout.fragment_mosque_find, container, false)
        initRecyclerView()
        return mosqueFindView
    }

    private fun initRecyclerView(){
        recyclerView = mosqueFindView.findViewById(R.id.mosque_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mosqueFindAdapter

        loadData()
    }
    private fun loadData() {
        firebaseService.getMasjidListItems().addOnCompleteListener{
            if (it.isSuccessful){
                masjidList = it.result!!.toObjects(MasjidModel::class.java) as ArrayList<MasjidModel>
                mosqueFindAdapter.items = masjidList
                mosqueFindAdapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }
    }

}