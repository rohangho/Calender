package com.example.calender.di

import com.example.calender.SetEventActivity
import dagger.Component
import javax.inject.Singleton


@Component(modules = [RoomModule::class, ViewModelModule::class])
@Singleton
interface MainComponent {

    fun inject(setMeetingActivity: SetEventActivity)
}