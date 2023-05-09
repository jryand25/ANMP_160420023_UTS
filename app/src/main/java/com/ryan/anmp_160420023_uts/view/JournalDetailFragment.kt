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
import com.ryan.anmp_160420023_uts.R
import com.ryan.anmp_160420023_uts.util.loadImage
import com.ryan.anmp_160420023_uts.viewmodel.JournalDetailViewModel

class JournalDetailFragment : Fragment() {
    private lateinit var journalViewModel: JournalDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            id = BookDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        journalViewModel = ViewModelProvider(this).get(JournalDetailViewModel::class.java)
        journalViewModel.fecth(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View){
        journalViewModel.journalLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtTitle).setText(it.title)
            view.findViewById<TextView>(R.id.txtAuthor).setText(it.author)
            view.findViewById<TextView>(R.id.txtYear).setText(it.year)
            view.findViewById<TextView>(R.id.txtTopic).setText(it.topic)
            view.findViewById<TextView>(R.id.txtAbstract).setText(it.abstract)
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoURL, view.findViewById<ProgressBar>(R.id.progressBar3))
        })
    }
}