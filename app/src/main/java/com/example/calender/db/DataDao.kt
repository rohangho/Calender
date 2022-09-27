package com.example.calender.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DataDao {
    @Insert
    fun insertDb(dbModel: DbModel)

    @Query("Select * from DbModel order by startTime")
    fun getAllQueryResult(): MutableList<DbModel>

}