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
import com.ryan.anmp_160420023_uts.model.Book

class BookListViewModel(application: Application): AndroidViewModel(application) {
    val bookLD = MutableLiveData<ArrayList<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "bookListTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        bookLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ryan.ip4amin.site/anmp-uts/book.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val sType = object : TypeToken<ArrayList<Book>>(){}.type
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)
                bookLD.value = result
                loadingLD.value = false

                Log.d("showVolley", result.toString())
            },{
                loadingLD.value = false
                bookLoadErrorLD.value = true
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