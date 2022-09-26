package com.example.calender

import android.app.Application
import com.example.calender.di.DaggerMainComponent
import com.example.calender.di.MainComponent
import com.example.calender.di.RoomModule

class MyApplication: Application() {

    private lateinit var appComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = initDagger()
    }

    private fun initDagger(): MainComponent = DaggerMainComponent.builder().roomModule(RoomModule(this)).build()
}