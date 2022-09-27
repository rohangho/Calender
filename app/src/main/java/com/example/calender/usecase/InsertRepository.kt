package com.example.calender.usecase

import com.example.calender.db.DbModel
import com.example.calender.db.MeetingDatabase
import java.util.*

import javax.inject.Inject

class InsertRepository @Inject constructor(private val roomInstance: MeetingDatabase) {

    suspend fun insertData(meetingName: String, startDate: Date, endDate: Date): String {
        return if (checkValidity(startDate, endDate).equals("Success")) {
            roomInstance.meetingDao().insertDb(DbModel(meetingName, startDate, endDate))
            "Success"
        } else
            "Fail"


    }

    fun checkValidity(startDate: Date, endDate: Date): String {
        val listofDetail = roomInstance.meetingDao().getAllQueryResult()
        var counter = 0
        for (i in listofDetail.indices) {
            if (startDate.time > listofDetail[i].endTime.time || endDate.time < listofDetail[i].startTime.time)
                counter += 1
            else
                break
        }

        return if (counter == listofDetail.size) {
            "Success"
        } else
            "Fail"

    }

}

