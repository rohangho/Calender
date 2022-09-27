package com.example.calender.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.calender.db.DbModel
import com.example.calender.model.Header


class ListDiaplayerAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var timeList: List<Any> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 2) {
            val v: View =
                LayoutInflater.from(parent.context)
                    .inflate(com.example.calender.R.layout.indiual_time_layout, parent, false)
            DataViewHolder(v)
        } else {
            val v: View =
                LayoutInflater.from(parent.context)
                    .inflate(
                        com.example.calender.R.layout.indiual_date_header_layout,
                        parent,
                        false
                    )
            HeaderViewHolder(v)
        }
    }


    override fun getItemCount(): Int {
        return timeList.size
    }


    fun updateList(updatedTimeList: List<Any>) {
        this.timeList = updatedTimeList
        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        return if (timeList.get(position) is Header)
            1
        else
            2
    }

    class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val timeList: TextView

        init {
            timeList = itemView.findViewById(com.example.calender.R.id.indivdualtTImeContent)

        }
    }

    class HeaderViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val headerList: TextView

        init {
            headerList = itemView.findViewById(com.example.calender.R.id.individualHeader)

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == 1)
            (holder as HeaderViewHolder).headerList.text = (timeList.get(position) as Header).date
        else
            (holder as DataViewHolder).timeList.text =
                (timeList.get(position) as DbModel).meetingName
    }
}
