package com.vepe.navigation.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vepe.navigation.model.Item

class MainViewModel: ViewModel() {

    private val items: MutableLiveData<List<String>> = MutableLiveData()

    var hasPremium = false

    val lastSeenItems = mutableSetOf<Item>()

    fun getItems(): LiveData<List<String>> = items

    fun prepareItems() {
        if (items.value == null) {
            val list = mutableListOf<String>()
            repeat(20) {
                list.add("${it + 1}. item")
            }
            items.postValue(list)
        }
        else items.postValue(items.value)
    }

    fun addItem(title: String) {
        val list = items.value as? MutableList<String>
        list?.add(title)
        items.postValue(list)
    }
}
