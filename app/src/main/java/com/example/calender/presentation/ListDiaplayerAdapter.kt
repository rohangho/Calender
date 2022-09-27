package com.example.calender.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calender.R
import com.example.calender.db.DbModel

class ListDiaplayerAdapter(private val context: Context) :
    RecyclerView.Adapter<ListDiaplayerAdapter.MyViewHolder>() {
    private var timeList: List<DbModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.indiual_time_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.timeList.text = timeList[position].meetingName + timeList[position].endTime
    }

    override fun getItemCount(): Int {
        return timeList.size
    }


    fun updateList(updatedTimeList: List<DbModel>) {
        this.timeList = updatedTimeList

    }

    class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val timeList: TextView

        init {
            timeList = itemView.findViewById(R.id.indivdualtTImeContent)

        }
    }
}
