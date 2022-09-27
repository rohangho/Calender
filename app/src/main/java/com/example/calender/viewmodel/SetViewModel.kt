package com.example.calender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _status: MutableLiveData<String> = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun insertMeetingDetail(meetingName: String, startDate: Date, endDate: Date) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _status.postValue(insertRepository.insertData(meetingName, startDate, endDate))
            }
        }
    }
}