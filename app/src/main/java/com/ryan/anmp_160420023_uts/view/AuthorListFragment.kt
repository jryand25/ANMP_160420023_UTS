package com.ryan.anmp_160420023_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ryan.anmp_160420023_uts.R
import com.ryan.anmp_160420023_uts.viewmodel.AuthorListViewModel
import com.ryan.anmp_160420023_uts.viewmodel.BookListViewModel

class AuthorListFragment : Fragment() {
    private lateinit var authorViewModel: AuthorListViewModel
    private var authorListAdapter = AuthorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorViewModel = ViewModelProvider(this).get(AuthorListViewModel::class.java)
        authorViewModel.refresh()

        var recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = authorListAdapter

        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener{
            view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            authorViewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }
    }

    fun observeViewModel(view: View){
        authorViewModel.authorLD.observe(viewLifecycleOwner, Observer{
            authorListAdapter.updateAuthorList(it)
        })
        authorViewModel.authorLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                view.findViewById<TextView>(R.id.txtError).visibility = View.VISIBLE
            }
            else{
                view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            }
        })
        authorViewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            }
            else{
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
            }
        })
    }
}