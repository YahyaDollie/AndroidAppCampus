package com.mosquefinder.app.Volley

import android.content.Context
import android.os.Environment
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class VolleyRequest {

    private var mRequestQueue: RequestQueue? = null
    private var context: Context? = null
    private var volleyInterface: VolleyInterface? = null
    val cacheDir:File = Environment.getExternalStorageDirectory()
    val cache = DiskBasedCache(cacheDir, 1024 * 1024)
    val network = BasicNetwork(HurlStack())
    val cacheEntry = Cache.Entry()
    val cacheRequestQueue = RequestQueue(cache, network).apply {
        start()
    }

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
//                volleyInterface!!.onResponse(response.toString())
                getCache(url)
            }, { error ->
                getCache(url)
//                volleyInterface!!.onResponse(error.message!!)
            })

        addToRequestQueue(getRequest)
    }

    fun getCache(url: String){
        Log.d("getCache: ", "Running")
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response -> volleyInterface!!.onResponse(response.toString()) }
        ) { error ->
            if (error != null) {
                volleyInterface!!.onResponse(error.message!!)
            }
        }
        cacheRequestQueue.add(stringRequest)
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