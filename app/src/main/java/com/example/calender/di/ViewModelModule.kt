package com.example.calender.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calender.di.common.ViewModelFactory
import com.example.calender.di.common.ViewModelKey
import com.example.calender.viewmodel.SetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewmodelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SetViewModel::class)
    internal abstract fun mainViewModel(viewModel: SetViewModel): ViewModel
}







