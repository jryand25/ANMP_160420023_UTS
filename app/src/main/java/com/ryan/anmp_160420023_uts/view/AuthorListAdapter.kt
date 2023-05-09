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
import com.ryan.anmp_160420023_uts.model.Author
import com.ryan.anmp_160420023_uts.util.loadImage

class AuthorListAdapter(val authorList:ArrayList<Author>): RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>() {
    class AuthorViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.author_list_item, parent, false)

        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtName).text = authorList[position].name
        holder.view.findViewById<TextView>(R.id.txtOrganization).text = authorList[position].organization

        holder.view.findViewById<TextView>(R.id.btnDetail).setOnClickListener {
            val action = AuthorListFragmentDirections.actionAuthorDetail(authorList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        imageView.loadImage(authorList[position].photoURL, progressBar)
    }

    override fun getItemCount(): Int {
        return authorList.size
    }

    fun updateAuthorList(newStudentList: ArrayList<Author>){
        authorList.clear()
        authorList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}