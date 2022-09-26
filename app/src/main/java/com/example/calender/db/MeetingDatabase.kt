package com.example.calender.db

import androidx.room.RoomDatabase

abstract class MeetingDatabase: RoomDatabase() {
    abstract fun insertMeeting(): DataDao
}