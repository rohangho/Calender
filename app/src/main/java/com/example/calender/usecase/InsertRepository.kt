package com.example.calender.usecase

import com.example.calender.db.DbModel
import com.example.calender.db.MeetingDatabase
import java.util.*

import javax.inject.Inject

class InsertRepository @Inject constructor(private val roomInstance: MeetingDatabase) {

    fun insertData(startDate: Date, endDate: Date) {
        roomInstance.meetingDao().insertDb(DbModel("fd", startDate, endDate))

    }

}

