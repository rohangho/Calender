package com.example.calender.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class MeetingDatabase : RoomDatabase() {
    abstract fun meetingDao(): DataDao
}