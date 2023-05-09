package com.ryan.anmp_160420023_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ryan.anmp_160420023_uts.R
import com.ryan.anmp_160420023_uts.model.Journal
import com.ryan.anmp_160420023_uts.util.loadImage

class JournalListAdapter(val journalList:ArrayList<Journal>): RecyclerView.Adapter<JournalListAdapter.JournalViewHolder>() {
    class JournalViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return JournalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtTitle).text = journalList[position].title
        holder.view.findViewById<TextView>(R.id.txtAuthor).text = journalList[position].author
        holder.view.findViewById<TextView>(R.id.txtYear).text = journalList[position].year

        holder.view.findViewById<TextView>(R.id.btnDetail).setOnClickListener {
            val action = JournalListFragmentDirections.actionJournalDetail(journalList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        imageView.loadImage(journalList[position].photoURL, progressBar)
    }

    override fun getItemCount(): Int {
        return journalList.size
    }

    fun updateJournalList(newStudentList: ArrayList<Journal>){
        journalList.clear()
        journalList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}