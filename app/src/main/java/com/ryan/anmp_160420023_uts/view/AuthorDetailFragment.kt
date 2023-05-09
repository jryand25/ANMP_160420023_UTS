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
import com.ryan.anmp_160420023_uts.viewmodel.AuthorDetailViewModel

class AuthorDetailFragment : Fragment() {
    private lateinit var authorViewModel: AuthorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
        if(arguments != null){
            id = BookDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        authorViewModel = ViewModelProvider(this).get(AuthorDetailViewModel::class.java)
        authorViewModel.fecth(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View){
        authorViewModel.authorLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtName).setText(it.name)
            view.findViewById<TextView>(R.id.txtPosition).setText(it.position)
            view.findViewById<TextView>(R.id.txtOrganization).setText(it.organization)
            view.findViewById<TextView>(R.id.txtProfile).setText(it.profile)
            view.findViewById<ImageView>(R.id.imageView2).loadImage(it.photoURL, view.findViewById<ProgressBar>(R.id.progressBar3))
        })
    }
}