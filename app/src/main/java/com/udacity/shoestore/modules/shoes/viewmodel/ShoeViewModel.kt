package com.udacity.shoestore.modules.shoes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

/**
 * This [ViewModel] holds in memory [Shoe] list and a separate [Shoe] that representes new shoes
 */
class ShoeViewModel : ViewModel() {

    val shoesMutableLiveData: MutableLiveData<MutableList<Shoe>> by lazy {
        MutableLiveData<MutableList<Shoe>>().also { it.value = mutableListOf() }
    }

    val shoeUpdate: MutableLiveData<Shoe?> = MutableLiveData()

    fun addShoe(shoe:Shoe){
        shoesMutableLiveData.value?.add(shoe)
        shoeUpdate.postValue(shoe)
    }

    fun clearShoes(){
        shoesMutableLiveData.value?.clear()
    }

    fun getShoe(shoeId: Int): Shoe?{
        return shoesMutableLiveData.value?.find { it.hashCode()==shoeId }
    }

}