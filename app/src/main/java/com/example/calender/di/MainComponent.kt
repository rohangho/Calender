package com.example.calender.di

import android.content.Context
import com.example.calender.MainActivity
import dagger.Component


@Component(modules = [RoomModule::class,ViewModelModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity?)
}