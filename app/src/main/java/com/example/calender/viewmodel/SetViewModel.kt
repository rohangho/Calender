package com.example.calender.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calender.usecase.InsertRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class SetViewModel @Inject constructor(
    private val insertRepository: InsertRepository
) : ViewModel() {


    fun insertMeetingDetail(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertRepository.insertData(startDate, endDate)
            }
        }
    }
}