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
import com.ryan.anmp_160420023_uts.model.Book

class BookDetailViewModel(application: Application): AndroidViewModel(application) {
    val bookLD = MutableLiveData<Book>()
    val TAG = "bookDetailTag"
    private var queue: RequestQueue? = null

    fun fecth(id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ryan.ip4amin.site/anmp-uts/book.php?id=" + id

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                val result = Gson().fromJson<Book>(it, Book::class.java)
                bookLD.value = result

                Log.d("showVolley", result.toString())
            },{
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