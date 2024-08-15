package com.example.openinappassignment.repository

import SharedPref
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.openinappassignment.HelperClass
import com.example.openinappassignment.model.ApiResponse
import com.google.gson.Gson

class LinkRepository() {

    var responseData: MutableLiveData<ApiResponse> = MutableLiveData()
    private val requestQueue: RequestQueue = Volley.newRequestQueue(HelperClass.context)

    private fun fetchDashboardData() {
        val url = "https://api.inopenapp.com/api/v1/dashboardNew"

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            Response.Listener { response ->
                val apiResponse = Gson().fromJson(response.toString(), ApiResponse::class.java)
                responseData.value = apiResponse
            },
            Response.ErrorListener { error ->
                // Handle the error here
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = mutableMapOf<String, String>()
                headers["Authorization"] =
                    "Bearer " + SharedPref(HelperClass.context).getAPIKey()
                headers["Cookie"] =
                    "connect.sid=s%3AzH_XZvhD6qUhkFWWnsSM1_eJCa-aXEqN.94NU4sEhlwAOTotdLK1OhQiQJzH0UFdWjVAPQxCliWc"
                return headers
            }
        }

        requestQueue.add(jsonObjectRequest)
    }

    fun getLiveData(): MutableLiveData<ApiResponse> {
        fetchDashboardData()
        return responseData
    }

}
