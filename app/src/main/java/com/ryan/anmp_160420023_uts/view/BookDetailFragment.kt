package com.ryan.anmp_160420023_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.ryan.anmp_160420023_uts.R
import com.ryan.anmp_160420023_uts.util.loadImage
import com.ryan.anmp_160420023_uts.viewmodel.BookDetailViewModel

class BookDetailFragment : Fragment() {
    private lateinit var bookViewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            id = BookDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        bookViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        bookViewModel.fecth(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View){
        bookViewModel.bookLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtTitle).setText(it.title)
            view.findViewById<TextView>(R.id.txtAuthor).setText(it.author)
            view.findViewById<TextView>(R.id.txtYear).setText(it.year)
            view.findViewById<TextView>(R.id.txtTotalPage).setText(it.totalPage)
            view.findViewById<TextView>(R.id.txtSynopsis).setText(it.synopsis)
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoURL, view.findViewById<ProgressBar>(R.id.progressBar3))
        })
    }
}