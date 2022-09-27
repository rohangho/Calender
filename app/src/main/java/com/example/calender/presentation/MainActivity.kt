package com.example.calender.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calender.databinding.ActivityMainBinding
import com.example.calender.db.DbModel
import com.example.calender.db.MeetingDatabase
import com.example.calender.di.DaggerMainComponent
import com.example.calender.di.RoomModule
import com.example.calender.model.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var meetingDatabase: MeetingDatabase

    private lateinit var binding: ActivityMainBinding

    private var listDatabaseAdapter = ListDiaplayerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder().roomModule(RoomModule(application)).build().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRecyclerView()

        binding.addNewEvent.setOnClickListener {
            startActivity(Intent(this, SetEventActivity::class.java))
        }

    }

    override fun onResume() {
        getAllList()
        super.onResume()
    }

    private fun initRecyclerView() {
        binding.listDisplayer.adapter = listDatabaseAdapter
        binding.listDisplayer.layoutManager = LinearLayoutManager(this)
    }

    private fun getAllList() {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfData = meetingDatabase.meetingDao().getAllQueryResult()
            val listOfUpdatedData = arrayListOf<DbModel>()
            for (i in 0 until listOfData.size) {
                if (listOfData[i].endTime.after(Date())) {
                    listOfUpdatedData.add(listOfData[i])
                }
            }

            val hashMap = HashMap<String, MutableList<DbModel>>()

            for (i in 0 until listOfUpdatedData.size) {
                if (!hashMap.contains(listOfUpdatedData[i].startTime.date.toString() + "/" + listOfUpdatedData[i].startTime.month.toString()))
                    hashMap[listOfUpdatedData[i].startTime.date.toString() + " " + SimpleDateFormat(
                        "MMM"
                    ).format(listOfUpdatedData[i].startTime.month)] =
                        arrayListOf(listOfUpdatedData[i])
                else
                    hashMap[listOfUpdatedData[i].startTime.date.toString() + " " + SimpleDateFormat(
                        "MMM"
                    ).format(listOfUpdatedData[i].startTime.month)]?.add(
                        listOfUpdatedData[i]
                    )
            }

            val listofDateAndDetail = mutableListOf<Any>()

            hashMap.forEach { (key, value) ->
                listofDateAndDetail.add(Header(key))
                for (i in 0 until value.size)
                    listofDateAndDetail.add(value[i])
            }




            withContext(Dispatchers.Main) {
                listDatabaseAdapter.updateList(listofDateAndDetail)
            }
        }
    }
}