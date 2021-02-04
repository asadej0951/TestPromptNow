package com.example.testpromptnow

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val statusButton = ObservableField(false)

    val onClickListener = MutableLiveData<String>()

    val onClickCheck = MutableLiveData<Int>()

    val edAdd = ObservableField("")

    val onCheckMessage = TextWatcherAdapter{
        s-> edAdd.set(s)
        checkEvenButton()
    }

    fun onClickAdd() {
        onClickListener.value = "Add"
    }

    fun onClickDelete() {
        onClickListener.value = "Delete"
    }

    fun checkEvenButton() {
        statusButton.set(edAdd.get()!!.isNotEmpty())
    }
}