package com.example.calender

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.calender.db.MeetingDatabase
import com.example.calender.di.DaggerMainComponent
import com.example.calender.di.RoomModule
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var meetingDatabase: MeetingDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder().roomModule(RoomModule(application)).build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAllList()
    }

    private fun getAllList() {
        lifecycleScope.launch {
            meetingDatabase.meetingDao().getAllQueryResult(Date())
        }
    }
}