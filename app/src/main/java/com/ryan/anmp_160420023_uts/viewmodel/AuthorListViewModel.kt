package com.ryan.anmp_160420023_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ryan.anmp_160420023_uts.model.Author

class AuthorListViewModel(application: Application): AndroidViewModel(application) {
    val authorLD = MutableLiveData<ArrayList<Author>>()
    val authorLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "authorListTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        authorLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ryan.ip4amin.site/anmp-uts/author.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<ArrayList<Author>>(){}.type
                val result = Gson().fromJson<ArrayList<Author>>(it, sType)
                authorLD.value = result
                loadingLD.value = false

                Log.d("showVolley", result.toString())
            },{
                loadingLD.value = false
                authorLoadErrorLD.value = true
                Log.d("showVolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}