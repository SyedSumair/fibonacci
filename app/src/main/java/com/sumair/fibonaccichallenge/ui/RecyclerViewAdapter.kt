package com.sumair.fibonaccichallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sumair.fibonaccichallenge.R


class RecyclerViewAdapter(private var list: MutableList<Long>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fibonacci_list_row, parent, false)
        return RegularViewHolder(view)
    }

    // binding data to each cell
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RegularViewHolder
        holder.febonacci_no.text = list[position].toString()
    }

    // add items to recycler view
    fun addItems(moreItems: MutableList<Long>) {
        val positionStart = list.size + 1
        list +=moreItems
        notifyItemRangeInserted(positionStart, moreItems.size);
    }

    inner class RegularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var febonacci_no: TextView

        init {
            febonacci_no = itemView.findViewById<View>(R.id.fibonacci_no) as TextView
        }

    }

}