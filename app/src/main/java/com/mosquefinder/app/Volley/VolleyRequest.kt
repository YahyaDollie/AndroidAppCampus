package com.mosquefinder.app.Volley

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class VolleyRequest {

    private var mRequestQueue: RequestQueue? = null
    private var context: Context? = null
    private var volleyInterface: VolleyInterface? = null

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null)
                mRequestQueue = Volley.newRequestQueue(context!!.applicationContext)
            return mRequestQueue!!
        }

    private constructor(context: Context, volleyInterface: VolleyInterface) {
        this.context = context
        this.volleyInterface = volleyInterface
        mRequestQueue = requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun getRequest(url: String) {
        Log.d("getRequest: ", mInstance.toString())
        val getRequest =
            JsonObjectRequest(Request.Method.GET, url, null, { response ->
                volleyInterface!!.onResponse(response.toString())
            }, { error ->
                volleyInterface!!.onResponse(error.message!!)
            })

        addToRequestQueue(getRequest)
    }

    companion object {
        private var mInstance: VolleyRequest? = null

        fun getInstance(context: Context?, volleyInterface: VolleyInterface): VolleyRequest {
            if (mInstance == null) {
                mInstance = context?.let { VolleyRequest(it, volleyInterface) }
            }
            return mInstance!!
        }

        fun resetInstance() {
            mInstance = null
        }
    }
}