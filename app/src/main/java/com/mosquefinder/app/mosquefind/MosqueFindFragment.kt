package com.mosquefinder.app.mosquefind

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.mosquefinder.app.models.MasjidModel

class MosqueFindFragment : Fragment(), LocationButtonListener {
    private lateinit var mosqueFindView: View
    private lateinit var recyclerView: RecyclerView
    private var masjidList: ArrayList<MasjidModel> = ArrayList()
    private var mosqueFindAdapter: MosqueFindAdapter = MosqueFindAdapter(masjidList)

    private val firebaseService: FirebaseService = FirebaseService()

    private val TAG = "Firebase Error"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mosqueFindView = inflater.inflate(R.layout.fragment_mosque_find, container, false)
        initRecyclerView()
        return mosqueFindView
    }

    private fun initRecyclerView() {
        recyclerView = mosqueFindView.findViewById(R.id.mosque_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mosqueFindAdapter

        loadData()
    }

    private fun loadData() {
        firebaseService.getMasjidListItems().addOnCompleteListener {
            if (it.isSuccessful) {
                masjidList =
                    it.result!!.toObjects(MasjidModel::class.java) as ArrayList<MasjidModel>
                mosqueFindAdapter.items = masjidList
                mosqueFindAdapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }
    }

    private fun handleMapLaunch(location: String, view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(location)
        val chooser = Intent.createChooser(intent, "Launch Maps")
        view.context.startActivity(chooser)
    }

    override fun locationButtonClicked(geoLocation: String, view: View) {
        handleMapLaunch(geoLocation, view)
    }

}