package com.example.calender.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*


@Dao
interface DataDao {
    @Insert
    fun insertDb(dbModel: DbModel)

    @Query("Select * from DbModel where endTime > :date")
    fun getAllQueryResult(date: Date): List<DbModel>

}