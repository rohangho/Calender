package com.example.calender.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DbModel(
    @PrimaryKey(autoGenerate = true) val uid:Int,
    @ColumnInfo(name = "meetingName") val meetingName:String,
    @ColumnInfo(name = "startTime")val startTime:Date,
    @ColumnInfo(name = "endTime") val endTime:Date
)
{
    constructor( meetingName: String,startTime: Date,endTime: Date): this(0,meetingName,startTime,endTime)
}
