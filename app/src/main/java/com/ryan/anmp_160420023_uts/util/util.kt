package com.ryan.anmp_160420023_uts.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.ryan.anmp_160420023_uts.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

public fun ImageView.loadImage(url: String?, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
}