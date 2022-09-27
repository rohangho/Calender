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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                listOfData[i].endTime.year = listOfData[i].endTime.year - 1900
                if (listOfData[i].endTime.time >= Date().time) {
                    listOfUpdatedData.add(listOfData[i])
                }
            }

            withContext(Dispatchers.Main) {
                listDatabaseAdapter.updateList(listOfUpdatedData)
            }
        }
    }
}