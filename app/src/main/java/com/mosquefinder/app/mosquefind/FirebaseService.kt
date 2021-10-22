package com.mosquefinder.app.mosquefind

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirebaseService {
    private val firebaseFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getMasjidListItems(): Task<QuerySnapshot> {
        return firebaseFirestore
            .collection("mosques")
            .get()
    }
}