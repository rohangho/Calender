package com.example.calender.di

import android.app.Application
import androidx.room.Room
import com.example.calender.db.MeetingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun provideRoomInstance(myApplication: Application): MeetingDatabase {
        return Room.databaseBuilder(
            application,
            MeetingDatabase::class.java, "sample-database-name"
        ).build()
    }

}