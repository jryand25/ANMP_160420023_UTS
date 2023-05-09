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
import com.ryan.anmp_160420023_uts.viewmodel.JournalListViewModel

class JournalListFragment : Fragment() {
    private lateinit var journalViewModel: JournalListViewModel
    private var journalListAdapter = JournalListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        journalViewModel = ViewModelProvider(this).get(JournalListViewModel::class.java)
        journalViewModel.refresh()

        var recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = journalListAdapter

        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener{
            view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
            journalViewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }
    }

    fun observeViewModel(view: View){
        journalViewModel.journalLD.observe(viewLifecycleOwner, Observer{
            journalListAdapter.updateJournalList(it)
        })
        journalViewModel.journalLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if(it == true){
                view.findViewById<TextView>(R.id.txtError).visibility = View.VISIBLE
            }
            else{
                view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            }
        })
        journalViewModel.loadingLD.observe(viewLifecycleOwner, Observer{
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