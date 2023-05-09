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
import com.ryan.anmp_160420023_uts.model.Book
import com.ryan.anmp_160420023_uts.util.loadImage

class BookListAdapter(val bookList:ArrayList<Book>): RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtTitle).text = bookList[position].title
        holder.view.findViewById<TextView>(R.id.txtAuthor).text = bookList[position].author
        holder.view.findViewById<TextView>(R.id.txtYear).text = bookList[position].year

        holder.view.findViewById<TextView>(R.id.btnDetail).setOnClickListener {
            val action = BookListFragmentDirections.actionBookDetail(bookList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        imageView.loadImage(bookList[position].photoURL, progressBar)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateBookList(newStudentList: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}