package com.example.calender.db

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface DataDao {
    @Insert
    fun insertDb(dbModel: DbModel)
}