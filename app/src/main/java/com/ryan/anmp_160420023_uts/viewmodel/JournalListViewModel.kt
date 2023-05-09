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
import com.ryan.anmp_160420023_uts.model.Journal

class JournalListViewModel(application: Application): AndroidViewModel(application) {
    val journalLD = MutableLiveData<ArrayList<Journal>>()
    val journalLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "journalListTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        journalLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ryan.ip4amin.site/anmp-uts/journal.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<ArrayList<Journal>>(){}.type
                val result = Gson().fromJson<ArrayList<Journal>>(it, sType)
                journalLD.value = result
                loadingLD.value = false

                Log.d("showVolley", result.toString())
            },{
                loadingLD.value = false
                journalLoadErrorLD.value = true
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